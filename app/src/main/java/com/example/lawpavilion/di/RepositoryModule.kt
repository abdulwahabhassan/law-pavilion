package com.example.lawpavilion.di

import android.net.ConnectivityManager
import com.example.lawpavilion.data.database.dao.FolderLocalDao
import com.example.lawpavilion.data.mapper.FolderMapper
import com.example.lawpavilion.data.repository.FolderRepositoryImpl
import com.example.lawpavilion.domain.repository.FolderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesFolderRepository(
        folderLocalDao: FolderLocalDao,
        folderMapper: FolderMapper
    ): FolderRepository {
        return FolderRepositoryImpl(folderLocalDao, folderMapper)
    }

}