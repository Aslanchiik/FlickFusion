package com.example.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.example.data.local.room.AppDatabase
import com.example.data.local.room.daos.PopularContentDao
import com.example.data.local.room.daos.RemoteKeyDao
import com.example.data.paging.PopularContentRemoteMediator
import com.example.data.remote.apiservices.PopularMoviesApiService
import com.example.data.remote.apiservices.PopularSeriesApiService
import com.example.domain.repositories.PopularMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PopularMovieRepositoryImpl @Inject constructor(
    private val popularContentDao: PopularContentDao,
    private val popularMoviesApiService: PopularMoviesApiService,
    private val popularSeriesApiService: PopularSeriesApiService,
    private val appDatabase: AppDatabase,
    private val remoteKeyDao: RemoteKeyDao
) : PopularMovieRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun fetchPopularContents() = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        remoteMediator = PopularContentRemoteMediator(
            popularMoviesApiService,
            popularSeriesApiService,
            appDatabase,
            popularContentDao,
            remoteKeyDao
        ),
        pagingSourceFactory = {
            popularContentDao.getAllMovies()
        },
        initialKey = 2
    ).flow.map {
        it.map { popularEntity ->
            popularEntity.toDomain()
        }
    }.flowOn(Dispatchers.IO)
}