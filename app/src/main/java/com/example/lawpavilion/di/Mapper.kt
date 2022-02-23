package com.example.lawpavilion.di

import com.example.lawpavilion.data.mapper.FolderMapper
import com.example.lawpavilion.data.mapper.JudgementMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    fun provideFolderMapper(
        judgementMapper: JudgementMapper
    ): FolderMapper {
        return FolderMapper(judgementMapper)
    }

    @Provides
    fun provideJudgementMapper(): JudgementMapper {
        return JudgementMapper()
    }

}