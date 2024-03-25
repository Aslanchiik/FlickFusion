package com.example.flickfusion.presentation.ui.fragments.home

import com.example.domain.usecases.FetchPopularContentsPagingUseCase
import com.example.flickfusion.base.BaseViewModel
import com.example.flickfusion.models.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchPopularContentsPagingUseCase: FetchPopularContentsPagingUseCase
) : BaseViewModel() {

    fun fetchPopularContents() =
        fetchPopularContentsPagingUseCase().collectPagingRequest { it.toUI() }
}