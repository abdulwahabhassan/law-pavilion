package com.example.lawpavilion.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.lawpavilion.data.database.AppDatabase
import com.example.lawpavilion.data.database.Converter
import com.example.lawpavilion.data.database.dao.FolderLocalDao
import com.example.lawpavilion.ui.utils.Constants.DATABASE_NAME
import com.example.lawpavilion.ui.utils.Constants.DATA_FILENAME
import com.example.lawpavilion.worker.DatabaseWorker
import com.example.lawpavilion.worker.DatabaseWorker.Companion.KEY_FILENAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext appContext: Context,
        typeConverters: Converter
    ): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .addTypeConverter(typeConverters)
            .addCallback(
                object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<DatabaseWorker>()
                            .setInputData(workDataOf(KEY_FILENAME to DATA_FILENAME))
                            .build()
                        WorkManager.getInstance(appContext).enqueue(request)
                    }
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideFolderLocalDAO(appDatabase: AppDatabase): FolderLocalDao {
        return appDatabase.folderLocalDao()
    }
}