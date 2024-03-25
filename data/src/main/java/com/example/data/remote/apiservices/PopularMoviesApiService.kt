package com.example.data.remote.apiservices

import com.example.data.remote.dtos.MovieModelDto
import com.example.data.remote.dtos.MovieResponseDto
import com.example.data.remote.dtos.PopularMovieModelDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PopularMoviesApiService {

    @GET("3/movie/popular")
    suspend fun fetchMovies(
        @Query("page") page: Int
    ): MovieResponseDto<PopularMovieModelDto>

    @GET("3/movie/{movie_id}")
    suspend fun fetchMovieDetail(
        @Path("movie_id") id: Int
    ): Response<MovieModelDto>
}