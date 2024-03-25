package com.example.domain.usecases

import com.example.domain.repositories.MovieRepository
import javax.inject.Inject

class FetchMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(id: Int) = movieRepository.fetchMovie(id)
}