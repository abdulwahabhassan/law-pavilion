package com.example.lawpavilion.di

import com.example.lawpavilion.data.mapper.FolderMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    fun provideFolderMapper(): FolderMapper {
        return FolderMapper()
    }


}