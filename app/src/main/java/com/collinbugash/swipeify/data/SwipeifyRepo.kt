package com.collinbugash.swipeify.data

import android.content.Context
import android.util.Log
import com.collinbugash.swipeify.presentation.settings.api.TrackFetcher
import com.collinbugash.swipeify.data.database.SwipeifyDao
import com.collinbugash.swipeify.data.database.SwipeifyDatabase
import com.collinbugash.swipeify.data.db.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SwipeifyRepo
private constructor(private val swipeifyDao: SwipeifyDao, private val coroutineScope: CoroutineScope = GlobalScope)
{
    companion object {
        private const val LOG_TAG = "448.SwipeifyRepo"
        private var INSTANCE: SwipeifyRepo? = null

        fun getInstance(context: Context): SwipeifyRepo {
            var instance = INSTANCE
            if(instance == null) {
                val db = SwipeifyDatabase.getInstance(context)
                instance = SwipeifyRepo(db.swipeifyDao)
                INSTANCE = instance
            }
            return instance
        }
    }

    val tracks: List<Track>

    fun getTracksByGenre(genre: String): Flow<List<Track>> = swipeifyDao.getTracksByGenre(genre)
    suspend fun getTrack(id: Int): Track? = swipeifyDao.getTrackById(id)
    fun addTrack(track: Track) {
        coroutineScope.launch {
            swipeifyDao.addTrack(track)
        }
    }
    fun deleteTrack(track: Track) {
        coroutineScope.launch {
            swipeifyDao.deleteTrack(track)
        }
    }

    // loops over the playlist id's and adds all the songs from them into db
    fun addPlaylists(playlists: List<Pair<String, String>>) {
        val trackFetcher = TrackFetcher()
        for (playlist in playlists) {
            trackFetcher.getPlaylistTracks(playlist.first, playlist.second, this)
        }
    }

    init {
        Log.d(LOG_TAG, "initializing repo list")

        val trackList = mutableListOf<Track>()
        tracks = trackList.toList()
    }
}