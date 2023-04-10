package com.collinbugash.swipeify.data

import android.content.Context
import android.util.Log
import com.collinbugash.swipeify.api.TrackFetcher
import com.collinbugash.swipeify.data.database.SwipeifyDao
import com.collinbugash.swipeify.data.database.SwipeifyDatabase
import com.collinbugash.swipeify.data.types.Track
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


    // playlist id's that hold songs for each genre
    // TODO add more genres later
    private val playlists = listOf(
        Pair("10858936162", "pop"),
        Pair("10475361602", "piano")
    )
    // loops over the playlist id's and adds all the songs from them into db
   suspend fun addPlaylists() {
        for (playlist in playlists) {
            val trackFetcher = TrackFetcher()
            trackFetcher.getPlaylist(playlist.first, playlist.second, this)
        }
    }

    init {
        Log.d(LOG_TAG, "initializing repo list")
        // TODO i think this is being called everytime app is opened, also don't know if this is the right place to add them
        coroutineScope.launch {
            addPlaylists()
        }
        val trackList = mutableListOf<Track>()
        tracks = trackList.toList()
    }
}