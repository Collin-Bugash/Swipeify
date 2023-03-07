package com.collinbugash.swipeify.api

import org.json.JSONObject
import retrofit2.http.Url
import java.util.Date

data class Track(
    val id: Int,
    val readable: Boolean,
    val title: String,
    val isrc: String,
    val preview: String,
)
