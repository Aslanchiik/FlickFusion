package com.example.data.remote.dtos

import com.example.data.local.room.entities.PopularContentEntity
import com.example.domain.models.PopularContentType
import com.google.gson.annotations.SerializedName

data class PopularSerialModelDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("name")
    val name: String
)

fun PopularSerialModelDto.toEntity() = PopularContentEntity(
    id, originalName, posterPath, name, PopularContentType.SERIES
)