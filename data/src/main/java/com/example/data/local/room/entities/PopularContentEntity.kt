package com.example.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.utils.DataMapper
import com.example.domain.models.PopularContentModel
import com.example.domain.models.PopularContentType

@Entity(tableName = "popular_entity")
data class PopularContentEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val originalTitle: String,
    val posterPath: String?,
    val title: String,
    var popularContentType: PopularContentType
) : DataMapper<PopularContentModel> {
    override fun toDomain() = PopularContentModel(
        id, originalTitle, posterPath, title, popularContentType
    )
}
