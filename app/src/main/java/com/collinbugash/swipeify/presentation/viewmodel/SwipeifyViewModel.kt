package com.collinbugash.swipeify.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.collinbugash.swipeify.api.TrackFetcher
import com.collinbugash.swipeify.data.Playlist
import com.collinbugash.swipeify.data.SwipeifyRepo
import com.collinbugash.swipeify.data.Track
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SwipeifyViewModel(private val swipeifyRepo: SwipeifyRepo) : ViewModel() {
    companion object {
        private const val LOG_TAG = "448.SwipeifyViewModel"
    }

    // playlist id's that hold songs for each genre
    // TODO add more genres later
    private val playlists = listOf(
        Pair("10858936162", "pop"),
        Pair("10475361602", "piano")
    )

    fun addTrack(trackToAdd: Track) {
        Log.d(LOG_TAG, "adding track $trackToAdd")
        swipeifyRepo.addTrack(trackToAdd)
    }

    // loops over the playlist id's and adds all the songs from them into db
    // TODO pretty sure this is the wrong place for this
    fun insertPlaylists() {
        for (playlist in playlists) {
            CoroutineScope(Dispatchers.IO).launch {
                val trackFetcher = TrackFetcher()
                trackFetcher.getPlaylist(playlist.first)
                // TODO couldn't figure this out, needs to wait on API call to finish and update the playlistState
                delay(2000)
                val playlistValue = trackFetcher.playlistState.value
                val jsonString = playlistValue?.tracks?.get("data").toString()
                if (jsonString.isNotEmpty() && jsonString != "null") {
                    val gson = Gson()
                    val allTracks = gson.fromJson(jsonString, Array<Track>::class.java).toList()

                    for (track in allTracks) {
                        track.genre = playlist.second
                        addTrack(track)
                    }
                    Log.d("API", "FINISHED ADDING")
                }
            }
        }
    }


    init {
        Log.d(LOG_TAG, "View Model Created")

        // TODO might need to move out of the viewmodel
        insertPlaylists()
    }
}