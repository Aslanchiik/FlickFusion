package com.example.flickfusion.models

import com.example.domain.models.GenresModel
import com.example.domain.models.MovieModel

data class MovieModelUI(
    val id: Int,
    val originalTitle: String,
    val releaseDate: String,
    val runtime: Int,
    val genres: List<GenresModel>,
    val posterPath: String,
    val overview: String,
)

fun MovieModel.toUI() = MovieModelUI(
    id, originalTitle, releaseDate, runtime, genres, posterPath, overview
)

