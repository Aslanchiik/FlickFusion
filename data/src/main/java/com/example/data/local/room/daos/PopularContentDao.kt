package com.example.data.local.room.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.room.entities.PopularContentEntity

@Dao
interface PopularContentDao {

    @Query("SELECT * FROM popular_entity")
    fun getAllMovies(): PagingSource<Int, PopularContentEntity>

    @Query("delete from popular_entity")
    suspend fun clearPopularContents()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPopularContents(pokemon: List<PopularContentEntity>)
}