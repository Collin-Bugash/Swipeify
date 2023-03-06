package com.collinbugash.swipeify.data

import android.util.Log

private const val LOG_TAG = "448.SongRepo"

//FOR TESTING PURPOSES MOST LIKELY NOT SURE YET
object SongRepo {
    init {
        Log.d(LOG_TAG, "Song Repo Initialized")
    }
    var songs = listOf(
        Song("Song 1", "Artist 1"),
        Song("Song 2", "Artist 2"),
        Song("Song 3", "Artist 3"),
        Song("Song 4", "Artist 4")
    )
}