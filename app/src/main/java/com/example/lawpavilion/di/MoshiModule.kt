package com.example.lawpavilion.di

import com.example.lawpavilion.data.database.entity.JudgementLocal
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.lang.reflect.Type
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoshiModule {

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

//    @Singleton
//    @Provides
//    fun providesConverterFactory(
//        moshi: Moshi
//    ): MoshiConverterFactory {
//        return MoshiConverterFactory.create(moshi)
//    }

    @Singleton
    @Provides
    fun providesJsonAdapterForJudgementType(
        moshi: Moshi
    ): JsonAdapter<JudgementLocal> =
        moshi.adapter(object : TypeToken<JudgementLocal>() {}.type)
}