package com.collinbugash.swipeify.data

import android.content.Context
import android.util.Log
import com.collinbugash.swipeify.api.TrackFetcher
import com.collinbugash.swipeify.data.database.SwipeifyDao
import com.collinbugash.swipeify.data.database.SwipeifyDatabase
import com.collinbugash.swipeify.data.db.Genre
import com.collinbugash.swipeify.data.db.LyricTrack
import com.collinbugash.swipeify.data.db.Lyrics
import com.collinbugash.swipeify.data.db.Track
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

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
    suspend fun addPlaylists(playlists: List<Pair<String, String>>, getNextTrack: () -> Unit) {
        val trackFetcher = TrackFetcher()
        for (playlist in playlists) {
            Log.d(LOG_TAG, "adding playlist: ${playlist.first}, ${playlist.second}")
            delay(400)
            coroutineScope.launch {
                trackFetcher.getPlaylistTracks(playlist.first, playlist.second, this@SwipeifyRepo)
            }
        }
        getNextTrack()
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

    fun getGenres(): Flow<List<Genre>> = swipeifyDao.getGenres()

    fun addGenre(genre: Genre) {
        coroutineScope.launch {
            swipeifyDao.addGenre(genre)
        }
    }
    fun deleteGenre(genre: Genre?) {
        if(genre != null) {
            coroutineScope.launch {
                swipeifyDao.deleteGenre(genre)
            }
        }
    }

    init {
        Log.d(LOG_TAG, "initializing repo list")

        val trackList = mutableListOf<Track>()
        tracks = trackList.toList()
    }
}