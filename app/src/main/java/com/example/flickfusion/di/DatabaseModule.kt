package com.example.flickfusion.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.room.AppDatabase
import com.example.data.local.room.daos.PopularContentDao
import com.example.data.local.room.daos.RemoteKeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "poke_api_database"
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun providePopularContentDao(appDatabase: AppDatabase): PopularContentDao =
        appDatabase.popularContentDao()

    @Singleton
    @Provides
    fun provideRemoteKeyDao(appDatabase: AppDatabase): RemoteKeyDao = appDatabase.remoteKeyDa()

}