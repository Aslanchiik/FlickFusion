package com.example.domain.models

data class PopularContentModel(
    val id: Int,
    val originalTitle: String,
    val posterPath: String?,
    val title: String,
    var popularContentType: PopularContentType
)
