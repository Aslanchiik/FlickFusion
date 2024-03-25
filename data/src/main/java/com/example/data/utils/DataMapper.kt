package com.example.data.utils

interface DataMapper<T> {
    fun toDomain(): T
}