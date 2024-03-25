package com.example.data.remote.dtos

import com.example.data.utils.DataMapper
import com.example.domain.models.SeriesModel
import com.google.gson.annotations.SerializedName

data class SeriesModelDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,
    @SerializedName("genres")
    val genres: List<GenresModelDto>,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("overview")
    val overview: String
) : DataMapper<SeriesModel> {
    override fun toDomain() = SeriesModel(
        id,
        originalName,
        firstAirDate,
        episodeRunTime,
        genres.map { it.toDomain() },
        posterPath,
        overview
    )
}
