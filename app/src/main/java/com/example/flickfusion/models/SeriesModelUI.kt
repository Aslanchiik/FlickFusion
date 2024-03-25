package com.example.flickfusion.models

import com.example.domain.models.GenresModel
import com.example.domain.models.SeriesModel

data class SeriesModelUI(
    val id: Int,
    val originalName: String,
    val firstAirDate: String,
    val episodeRunTime: List<Int>,
    val genres: List<GenresModel>,
    val posterPath: String,
    val overview: String
)

fun SeriesModel.toUI() = SeriesModelUI(
    id, originalName, firstAirDate, episodeRunTime, genres, posterPath, overview
)
