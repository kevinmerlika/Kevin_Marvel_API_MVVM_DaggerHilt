package com.example.kevin_marvel_mvvm_clean_architecture.Di

import com.example.kevin_marvel_mvvm_clean_architecture.Data.DataSource.MarvelApi
import com.example.kevin_marvel_mvvm_clean_architecture.Data.Repository.MarvelRepositoryImplement
import com.example.kevin_marvel_mvvm_clean_architecture.Domain.Repository.MarvelRepository
import com.example.kevin_marvel_mvvm_clean_architecture.Util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMarvelApi(): MarvelApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApi::class.java)
    }
    @Provides
    @Singleton
    fun provideMarvelRepository(api:MarvelApi):MarvelRepository{
        return MarvelRepositoryImplement(api)
    }
}