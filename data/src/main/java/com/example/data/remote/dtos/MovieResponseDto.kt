package com.example.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class MovieResponseDto<T>(

    @SerializedName("page") var page: Int? = null,

    @SerializedName("results")
    val results: List<T>,

    @SerializedName("total_results")
    val totalResults: Int? = null,

    @SerializedName("total_pages")
    val totalPages: Int? = null
)
