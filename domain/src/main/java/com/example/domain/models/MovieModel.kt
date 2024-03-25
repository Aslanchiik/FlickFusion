package com.example.domain.models

data class MovieModel(
    val id: Int,
    val originalTitle: String,
    val releaseDate: String,
    val runtime: Int,
    val genres: List<GenresModel>,
    val posterPath: String,
    val overview: String,
)
