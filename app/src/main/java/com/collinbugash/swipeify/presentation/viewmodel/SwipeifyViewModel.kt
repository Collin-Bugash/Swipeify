package com.collinbugash.swipeify.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.collinbugash.swipeify.data.SwipeifyRepo
import com.collinbugash.swipeify.data.db.Track

class SwipeifyViewModel(private val swipeifyRepo: SwipeifyRepo) : ViewModel() {
    companion object {
        private const val LOG_TAG = "448.SwipeifyViewModel"
    }

    fun addTrack(trackToAdd: Track) {
        Log.d(LOG_TAG, "adding track $trackToAdd")
        swipeifyRepo.addTrack(trackToAdd)
    }
    suspend fun addPlaylists(){
        Log.d(LOG_TAG, "adding playlists songs to db")
        swipeifyRepo.addPlaylists()
    }

    init {
        Log.d(LOG_TAG, "View Model Created")
    }
}