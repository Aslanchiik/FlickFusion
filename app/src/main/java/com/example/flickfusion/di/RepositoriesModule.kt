package com.example.flickfusion.di

import com.example.data.repositories.MovieRepositoryImpl
import com.example.data.repositories.PopularMovieRepositoryImpl
import com.example.domain.repositories.MovieRepository
import com.example.domain.repositories.PopularMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun providePopularMovieRepository(
        repositoryIml: PopularMovieRepositoryImpl
    ): PopularMovieRepository

    @Binds
    abstract fun provideMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}