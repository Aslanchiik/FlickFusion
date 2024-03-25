package com.example.domain.usecases

import com.example.domain.repositories.PopularMovieRepository
import javax.inject.Inject

class FetchPopularContentsPagingUseCase @Inject constructor(
    private val popularMovieRepository: PopularMovieRepository
) {
    operator fun invoke() = popularMovieRepository.fetchPopularContents()
}