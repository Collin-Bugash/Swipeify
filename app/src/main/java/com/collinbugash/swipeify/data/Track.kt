package com.collinbugash.swipeify.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.json.JSONObject
import retrofit2.http.Url
import java.util.Date

@Entity(tableName = "track")
data class Track(
    @PrimaryKey
    val id: Int,
    val readable: Boolean,
    val title: String,
//    val isrc: String,
    val preview: String,
    var genre: String = ""
)
