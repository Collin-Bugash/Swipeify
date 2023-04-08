package com.collinbugash.swipeify.data

import com.google.gson.JsonObject

data class Playlist(
    val id: String,
    val nbTracks: Int,
    var tracks: JsonObject
)
