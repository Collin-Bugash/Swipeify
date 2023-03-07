package com.collinbugash.swipeify.api

import android.util.Log
import androidx.compose.runtime.collectAsState
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

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://api.deezer.com/").addConverterFactory(
            GsonConverterFactory.create()).build()

        swipeifyService = retrofit.create(SwipeifyService::class.java)
    }

    fun getTrack(trackId: String) {
        val swipeifyRequest = swipeifyService.getTrack(trackId)
        swipeifyRequest.enqueue(object : Callback<Track> {
            override fun onFailure(call: Call<Track>, t: Throwable) {
                mTrackState.update { null }
                Log.d("API", "ERROR: ${t.message}")
            }
            override fun onResponse(call: Call<Track>,
                                    response: Response<Track>
            ) {
                val swipeifyResponse = response.body()
                mTrackState.update { swipeifyResponse }
                Log.d("API", "Response: $swipeifyResponse")
            }
        })
    }
}