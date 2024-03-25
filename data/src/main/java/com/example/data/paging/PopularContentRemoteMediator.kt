package com.example.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.data.local.room.AppDatabase
import com.example.data.local.room.daos.PopularContentDao
import com.example.data.local.room.daos.RemoteKeyDao
import com.example.data.local.room.entities.PopularContentEntity
import com.example.data.local.room.entities.RemoteKeyEntity
import com.example.data.remote.apiservices.PopularMoviesApiService
import com.example.data.remote.apiservices.PopularSeriesApiService
import com.example.data.remote.dtos.toEntity
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class PopularContentRemoteMediator(
    private val popularMoviesApiService: PopularMoviesApiService,
    private val popularSeriesApiService: PopularSeriesApiService,
    private val appDatabase: AppDatabase,
    private val movieDao: PopularContentDao,
    private val remoteKeyDao: RemoteKeyDao
) : RemoteMediator<Int, PopularContentEntity>() {

    override suspend fun initialize(): InitializeAction {
        val remoteKey = appDatabase.withTransaction {
            remoteKeyDao.getKeyByMovie("popular")
        } ?: return InitializeAction.LAUNCH_INITIAL_REFRESH

        val cacheTimeout = TimeUnit.HOURS.convert(1, TimeUnit.MILLISECONDS)

        return if ((System.currentTimeMillis() - remoteKey.last_updated) >= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PopularContentEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    1
                }

                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }

                LoadType.APPEND -> {
                    val remoteKey = appDatabase.withTransaction {
                        remoteKeyDao.getKeyByMovie("popular")
                    } ?: return MediatorResult.Success(true)

                    if (remoteKey.next_page == null) {
                        return MediatorResult.Success(true)
                    }
                    remoteKey.next_page
                }
            }


            val movies = popularMoviesApiService.fetchMovies(page)
            val series = popularSeriesApiService.fetchSeries(page)

            val results = mutableListOf<PopularContentEntity>()
            results.addAll(movies.results.map { it.toEntity() })
            results.addAll(series.results.map { it.toEntity() })

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    movieDao.clearPopularContents()
                }

                val nextPage = if (results.isEmpty()) {
                    null
                } else {
                    page + 1
                }

                remoteKeyDao.insertKey(
                    RemoteKeyEntity(
                        next_page = nextPage,
                        last_updated = System.currentTimeMillis()
                    )
                )
                results.shuffle()
                movieDao.insertAllPopularContents(results)
            }

            MediatorResult.Success(
                endOfPaginationReached = results.isEmpty()
            )
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }
}