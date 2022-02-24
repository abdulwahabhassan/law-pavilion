package com.example.lawpavilion.di

import android.content.Context
import com.example.lawpavilion.data.database.AppDatabase
import com.example.lawpavilion.data.database.dao.FolderLocalDao
import com.squareup.moshi.JsonAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFolderLocalDAO(appDatabase: AppDatabase): FolderLocalDao {
        return appDatabase.folderLocalDao()
    }

    @Provides
    @Singleton
    fun providesAppDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase {
        return AppDatabase.getInstance(appContext)
    }

}