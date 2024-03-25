package com.example.data.remote.apiservices

import com.example.data.remote.dtos.MovieResponseDto
import com.example.data.remote.dtos.PopularSerialModelDto
import com.example.data.remote.dtos.SeriesModelDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PopularSeriesApiService {

    @GET("3/tv/popular")
    suspend fun fetchSeries(
        @Query("page") page: Int
    ): MovieResponseDto<PopularSerialModelDto>

    @GET("3/tv/{series_id}")
    suspend fun fetchSeriesDetail(
        @Path("series_id") id: Int
    ): SeriesModelDto
}