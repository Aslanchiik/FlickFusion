package com.example.data.remote.dtos

import com.example.data.utils.DataMapper
import com.example.domain.models.MovieModel
import com.google.gson.annotations.SerializedName

data class MovieModelDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("genres")
    val genresModel: List<GenresModelDto>,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("overview")
    val overview: String
) : DataMapper<MovieModel> {
    override fun toDomain() = MovieModel(
        id,
        originalTitle,
        releaseDate,
        runtime,
        genresModel.map { it.toDomain() },
        posterPath,
        overview
    )
}