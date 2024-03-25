package com.example.domain.repositories

import com.example.domain.models.MovieModel
import com.example.domain.utils.RemoteWrapper

interface MovieRepository {

    fun fetchMovie(id: Int): RemoteWrapper<MovieModel>

}