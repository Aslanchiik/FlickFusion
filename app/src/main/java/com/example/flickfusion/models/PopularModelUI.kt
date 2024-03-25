package com.example.flickfusion.models

import com.example.domain.models.PopularContentModel
import com.example.domain.models.PopularContentType
import com.example.flickfusion.base.IBaseDiffModel

data class PopularModelUI(
    override val id: Int,
    val originalTitle: String,
    val posterPath: String?,
    val title: String,
    val popularContentType: PopularContentType
) : IBaseDiffModel

fun PopularContentModel.toUI() = PopularModelUI(
    id, originalTitle, posterPath, title, popularContentType
)
