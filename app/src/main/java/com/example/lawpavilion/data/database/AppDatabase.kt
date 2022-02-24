package com.example.lawpavilion.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.lawpavilion.data.database.dao.FolderLocalDao
import com.example.lawpavilion.data.database.entity.FolderLocal
import com.example.lawpavilion.ui.utils.Constants
import com.example.lawpavilion.data.worker.DatabaseWorker

@Database(entities = [FolderLocal::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun folderLocalDao(): FolderLocalDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(appContext: Context): AppDatabase {
            return Room.databaseBuilder(
                appContext,
                AppDatabase::class.java,
                Constants.DATABASE_NAME
            )
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<DatabaseWorker>()
                                .setInputData(workDataOf(DatabaseWorker.KEY_FILENAME to Constants.DATA_FILENAME))
                                .build()
                            WorkManager.getInstance(appContext).enqueue(request)
                        }
                    }
                )
                .build()
        }

    }

}