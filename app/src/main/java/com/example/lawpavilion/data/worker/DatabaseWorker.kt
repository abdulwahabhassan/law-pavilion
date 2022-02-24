package com.example.lawpavilion.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.lawpavilion.data.database.AppDatabase
import com.example.lawpavilion.data.database.entity.FolderLocal
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import timber.log.Timber

class DatabaseWorker (
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val folderType = object : TypeToken<List<FolderLocal>>() {}.type
                        val folders: List<FolderLocal> = Gson().fromJson(jsonReader, folderType)

                        Timber.d("folders $folders")

                        val database = AppDatabase.getInstance(applicationContext)
                        database.folderLocalDao().insertAllFolders(folders)
                        Timber.d("Success database - all set in database")
                        Result.success()
                    }
                }
            } else {
                Timber.d("Error database - no valid filename")
                Result.failure()
            }
        } catch (ex: Exception) {
            Timber.d("Error database", ex)
            Result.failure()
        }
    }

    companion object {
        const val KEY_FILENAME = "DATA_FILENAME"
    }
}