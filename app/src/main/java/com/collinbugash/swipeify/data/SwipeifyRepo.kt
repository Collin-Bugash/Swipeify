package com.collinbugash.swipeify.data

import android.content.Context
import android.util.Log
import com.collinbugash.swipeify.api.TrackFetcher
import com.collinbugash.swipeify.data.database.SwipeifyDao
import com.collinbugash.swipeify.data.database.SwipeifyDatabase
import com.collinbugash.swipeify.data.db.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
    fun deleteTrack(track: Track?) {
        if(track != null) {
            coroutineScope.launch {
                swipeifyDao.deleteTrack(track)
            }
        }
    }

    suspend fun getRandomTrack(genres: List<String>): Track? {
        Log.d("448.GENRES", genres.toString())
        return withContext(Dispatchers.IO) {
            swipeifyDao.getRandomTrackByGenres(genres)
        }
    }

    // loops over the playlist id's and adds all the songs from them into db
    fun addPlaylists(playlists: List<Pair<String, String>>) {
        val trackFetcher = TrackFetcher()
        for (playlist in playlists) {
            trackFetcher.getPlaylistTracks(playlist.first, playlist.second, this)
        }
    }

    // Gets the lyrivs given an id
    fun getLyrics(trackId: String) {
        val trackFetcher = TrackFetcher()
        trackFetcher.getTrackLyrics(trackId)
    }

    fun updateTrack(track: Track?) {
        if(track != null) {
            coroutineScope.launch {
                swipeifyDao.updateTrackFavorite(track)
            }
        }
    }

//    suspend fun getFavoritedSongs(): List<Track>  {
//        return withContext(Dispatchers.IO) {
//            swipeifyDao.getFavoritedSongs()
//        }
//    }

    fun getFavoritedSongs(): Flow<List<Track>> = swipeifyDao.getFavoritedSongs()

    init {
        Log.d(LOG_TAG, "initializing repo list")

        val trackList = mutableListOf<Track>()
        tracks = trackList.toList()
    }
}