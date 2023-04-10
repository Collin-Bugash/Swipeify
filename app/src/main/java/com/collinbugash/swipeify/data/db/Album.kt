package com.collinbugash.swipeify.data.db

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class Album(
    val id: String,
    @ColumnInfo(name = "images")
    val images: List<Image>
)

data class Image(
    val height: Int,
    val url: String,
    val width: Int
)
