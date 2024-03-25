package com.example.flickfusion.presentation.ui.fragments.home.detail

import com.example.domain.usecases.FetchMovieUseCase
import com.example.flickfusion.base.BaseViewModel
import com.example.flickfusion.models.MovieModelUI
import com.example.flickfusion.models.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailHomeViewModel @Inject constructor(
    private val fetchMovieUseCase: FetchMovieUseCase
) : BaseViewModel() {

    private val _movieState = MutableUIStateFlow<MovieModelUI>()
    val movieState = _movieState.asStateFlow()

    fun fetchDetailHotel(id: Int) {
        fetchMovieUseCase(id).collectNetworkRequest(_movieState) {
            it.toUI()
        }
    }
}