package com.example.data.repositories

import com.example.data.base.BaseRepository
import com.example.data.remote.apiservices.PopularMoviesApiService
import com.example.domain.repositories.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviesApiService: PopularMoviesApiService,
) : BaseRepository(), MovieRepository {

    override fun fetchMovie(id: Int) = doNetworkRequestWithMapping {
        moviesApiService.fetchMovieDetail(id)
    }
}