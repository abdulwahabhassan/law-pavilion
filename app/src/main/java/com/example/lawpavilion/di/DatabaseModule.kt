package com.example.lawpavilion.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.lawpavilion.data.database.AppDatabase
import com.example.lawpavilion.data.database.Converter
import com.example.lawpavilion.data.database.dao.FolderLocalDao
import com.example.lawpavilion.ui.utils.Constants.DATABASE_NAME
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
            .addCallback(object : RoomDatabase.Callback() {

                //prepopulate the database
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

            }

        })

            .build()
    }

    @Provides
    @Singleton
    fun provideFolderLocalDAO(appDatabase: AppDatabase): FolderLocalDao {
        return appDatabase.folderLocalDao()
    }
}