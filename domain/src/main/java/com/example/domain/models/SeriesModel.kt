package com.example.domain.models

data class SeriesModel(
    val id: Int,
    val originalName: String,
    val firstAirDate: String,
    val episodeRunTime: List<Int>,
    val genres: List<GenresModel>,
    val posterPath: String,
    val overview: String
)
