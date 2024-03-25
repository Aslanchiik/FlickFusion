package com.example.data.remote.dtos

import com.example.data.utils.DataMapper
import com.example.domain.models.GenresModel
import com.google.gson.annotations.SerializedName

data class GenresModelDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
) : DataMapper<GenresModel> {
    override fun toDomain() = GenresModel(
        id, name
    )
}
