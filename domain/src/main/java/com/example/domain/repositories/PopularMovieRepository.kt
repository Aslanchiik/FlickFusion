package com.example.domain.repositories

import androidx.paging.PagingData
import com.example.domain.models.PopularContentModel
import kotlinx.coroutines.flow.Flow

interface PopularMovieRepository {

    fun fetchPopularContents(): Flow<PagingData<PopularContentModel>>
}