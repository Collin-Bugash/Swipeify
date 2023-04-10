package com.collinbugash.swipeify.data.db

data class PlaylistTracks(
    val tracks: Tracks
)

data class Tracks(
    val items: List<TrackInfo>
)

data class TrackInfo(
    val track: Track
)