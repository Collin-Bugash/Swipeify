package com.collinbugash.swipeify.data.types

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "track")
data class Track(
    @PrimaryKey
    val id: Int,
    val readable: Boolean,
    val title: String,
    val preview: String,
    var genre: String = "",
    @Embedded(prefix = "album_")
    val album: Album,
    @Embedded(prefix = "artist_")
    val artist: Artist
)
