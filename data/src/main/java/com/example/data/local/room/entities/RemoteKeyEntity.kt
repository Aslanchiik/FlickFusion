package com.example.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = "popular",
    val next_page: Int?,
    val last_updated: Long
)
