package com.collinbugash.swipeify.api

import android.util.Log
import com.collinbugash.swipeify.data.SwipeifyRepo
import com.collinbugash.swipeify.data.db.LyricTrack
import com.collinbugash.swipeify.data.db.Lyrics
import com.collinbugash.swipeify.data.db.PlaylistTracks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TrackFetcher {
    private val swipeifyService: SwipeifyService
    private val mTrackLyricsState = MutableStateFlow<LyricTrack?>(null)
    val trackLyricsState: StateFlow<LyricTrack?>
        get() = mTrackLyricsState.asStateFlow()

    private val mPlaylistTracksState = MutableStateFlow<PlaylistTracks?>(null)
    val playlistTracksState: StateFlow<PlaylistTracks?>
        get() = mPlaylistTracksState.asStateFlow()

    private val authInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("X-RapidAPI-Key", "a8feb133f0mshbf44f45aa53e766p15a02ejsn3c917a18b7eb")
            .addHeader("X-RapidAPI-Host", "spotify23.p.rapidapi.com")
            .build()
        chain.proceed(request)
    }

    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://spotify23.p.rapidapi.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        swipeifyService = retrofit.create(SwipeifyService::class.java)
    }

    // Function to make an API call for a Track's lyrics
    fun getTrackLyrics(trackId: String) {
        Log.d("448.API", "Track ID: $trackId")
        val swipeifyRequest = swipeifyService.getTrackLyrics(trackId)
        swipeifyRequest.enqueue(object : Callback<LyricTrack> {
            override fun onFailure(call: Call<LyricTrack>, t: Throwable) {
                mTrackLyricsState.update { null }
                Log.d("448.API", "ERROR WITH FETCHING TRACK LYRICS: ${t.message}")
            }
            override fun onResponse(call: Call<LyricTrack>, response: Response<LyricTrack>) {
                val swipeifyResponse = response.body()
                if (swipeifyResponse == null) {
                    Log.d("448.API", "Response to get lyrics is null!")
                    mTrackLyricsState.update { null }
                } else {
                    mTrackLyricsState.update { swipeifyResponse }
                    Log.d("448.APILyrics", mTrackLyricsState.value.toString())
                }
                Log.d("448.API", "Response (TrackLyrics): $swipeifyResponse")
            }
        })
    }

    // Function to make an API call for a playlist
    fun getPlaylistTracks(playlistId: String, genre: String, swipeifyRepo: SwipeifyRepo) {
        val swipeifyRequest = swipeifyService.getPlaylistTracks(playlistId)
        swipeifyRequest.enqueue(object: Callback<PlaylistTracks> {
            override fun onFailure(call: Call<PlaylistTracks>, t: Throwable) {
                mPlaylistTracksState.update { null }
                Log.d("448.API", "ERROR WITH FETCHING PLAYLIST TRACKS: ${t.message}")
            }
            override fun onResponse(call: Call<PlaylistTracks>, response: Response<PlaylistTracks>) {
                val swipeifyResponse = response.body()
                Log.d("448.API PLAYLIST ID", playlistId)
                swipeifyResponse?.let { playlistTracks ->
                    mPlaylistTracksState.update { playlistTracks }
                        for (item in playlistTracks.tracks.items) {
                            if(item.track.preview_url != null) {
                                item.track.genre = genre
                                swipeifyRepo.addTrack(item.track)
                            }
//                            Log.d("API: CURRENT TRACK", item.track.preview_url)
                        }
                        Log.d("448.API", "FINISHED ADDING")
                }
            }
        })
    }
}