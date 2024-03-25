package com.example.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.room.daos.PopularContentDao
import com.example.data.local.room.daos.RemoteKeyDao
import com.example.data.local.room.entities.PopularContentEntity
import com.example.data.local.room.entities.RemoteKeyEntity

@Database(
    entities = [PopularContentEntity::class, RemoteKeyEntity::class],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun popularContentDao(): PopularContentDao
    abstract fun remoteKeyDa(): RemoteKeyDao
}