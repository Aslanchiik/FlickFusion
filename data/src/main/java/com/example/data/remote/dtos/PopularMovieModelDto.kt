package com.example.data.remote.dtos

import com.example.data.local.room.entities.PopularContentEntity
import com.example.domain.models.PopularContentType
import com.google.gson.annotations.SerializedName

data class PopularMovieModelDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("title")
    val title: String
)

fun PopularMovieModelDto.toEntity() = PopularContentEntity(
    id, originalTitle, posterPath, title, PopularContentType.MOVIE
)