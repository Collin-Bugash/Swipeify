package com.collinbugash.swipeify.data.types

import com.google.gson.JsonObject

data class Playlist(
    val id: String,
    val nbTracks: Int,
    var tracks: TrackList
)

data class TrackList(
    val data: List<Track>
)
