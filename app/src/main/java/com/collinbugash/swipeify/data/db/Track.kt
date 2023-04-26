package com.collinbugash.swipeify.data.db

import androidx.room.*

@Entity(tableName = "track")
data class Track(
    @PrimaryKey
    val id: String,
    val preview_url: String,
    val name: String,
    @Embedded(prefix="_album")
    val album: Album,
    @ColumnInfo(name = "artists")
    val artists: List<Artist>,
    var genre: String = "",
    var favorite: Boolean = false
)
