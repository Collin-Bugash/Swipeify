package com.collinbugash.swipeify.api

import com.collinbugash.swipeify.data.types.Playlist
import com.collinbugash.swipeify.data.types.Track
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SwipeifyService {
    @GET("track/{trackId}")
    fun getTrack(@Path("trackId") trackId: String): Call<Track>
    @GET("playlist/{playlistId}")
    fun getPlaylist(@Path("playlistId") playlistId: String): Call<Playlist>
}


