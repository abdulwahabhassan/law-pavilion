package com.example.lawpavilion.di

import com.example.lawpavilion.domain.repository.FolderRepository
import com.example.lawpavilion.domain.usecase.RetrieveAllFoldersUseCase
import com.example.lawpavilion.domain.usecase.RetrieveFolderUseCase
import com.example.lawpavilion.domain.usecase.SaveFolderUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @ViewModelScoped
    @Provides
    fun providesRetrieveAllFoldersUseCase(
        folderRepository: FolderRepository
    ): RetrieveAllFoldersUseCase {
        return RetrieveAllFoldersUseCase(folderRepository)
    }

    @ViewModelScoped
    @Provides
    fun providesRetrieveFolderUseCase(
        folderRepository: FolderRepository
    ): RetrieveFolderUseCase {
        return RetrieveFolderUseCase(folderRepository)
    }

    @ViewModelScoped
    @Provides
    fun providesSaveFolderUseCase(
        folderRepository: FolderRepository
    ): SaveFolderUseCase {
        return SaveFolderUseCase(folderRepository)
    }
}