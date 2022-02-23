package com.example.lawpavilion.worker

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
import javax.inject.Inject

class DatabaseWorker @Inject constructor(
    private val appDatabase: AppDatabase,
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

                        appDatabase.folderLocalDao().insertAllFolders(folders)

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