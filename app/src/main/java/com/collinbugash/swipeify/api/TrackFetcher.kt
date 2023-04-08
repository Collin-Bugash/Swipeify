package com.collinbugash.swipeify.api

import android.util.Log
import com.collinbugash.swipeify.data.Playlist
import com.collinbugash.swipeify.data.Track
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TrackFetcher {
    private val swipeifyService: SwipeifyService
    private val mTrackState = MutableStateFlow<Track?>(null)
    val trackState: StateFlow<Track?>
        get() = mTrackState.asStateFlow()

    private val mPlaylistState = MutableStateFlow<Playlist?>(null)
    val playlistState: StateFlow<Playlist?>
        get() = mPlaylistState.asStateFlow()

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://api.deezer.com/").addConverterFactory(
            GsonConverterFactory.create()).build()

        swipeifyService = retrofit.create(SwipeifyService::class.java)
    }

    // Function to make an API call for a Track
    fun getTrack(trackId: String) {
        val swipeifyRequest = swipeifyService.getTrack(trackId)
        swipeifyRequest.enqueue(object : Callback<Track> {
            override fun onFailure(call: Call<Track>, t: Throwable) {
                mTrackState.update { null }
                Log.d("API", "ERROR WITH FETCHING TRACK: ${t.message}")
            }
            override fun onResponse(call: Call<Track>,
                                    response: Response<Track>
            ) {
                val swipeifyResponse = response.body()
                mTrackState.update { swipeifyResponse }
                Log.d("API", "Response (Track): $swipeifyResponse")
            }
        })
    }

    // Function to make an API call for a playlist
    suspend fun getPlaylist(playlistId: String) {
        val swipeifyRequest = swipeifyService.getPlaylist(playlistId)
        swipeifyRequest.enqueue(object: Callback<Playlist> {
            override fun onFailure(call: Call<Playlist>, t: Throwable) {
                mPlaylistState.update { null }
                Log.d("API", "ERROR WITH FETCHING PLAYLIST: ${t.message}")
            }
            override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                val swipeifyResponse = response.body()
                swipeifyResponse?.let { playlist ->
                    mPlaylistState.update { playlist }
                    Log.d("API", "Response (Playlist): $swipeifyResponse")
                }
            }
        })
    }
}