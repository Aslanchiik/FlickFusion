package com.example.flickfusion.di

import com.example.data.remote.RetrofitClient
import com.example.data.remote.apiservices.PopularMoviesApiService
import com.example.data.remote.apiservices.PopularSeriesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesPopularMovieApiService(retrofitClient: RetrofitClient): PopularMoviesApiService =
        retrofitClient.provideApiService()

    @Singleton
    @Provides
    fun providesPopularSeriesApiService(retrofitClient: RetrofitClient): PopularSeriesApiService =
        retrofitClient.provideApiService()

}