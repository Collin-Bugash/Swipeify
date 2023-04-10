package com.collinbugash.swipeify.api

import com.collinbugash.swipeify.data.db.Lyrics
import com.collinbugash.swipeify.data.db.PlaylistTracks
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface SwipeifyService {
    @GET("playlist_tracks/")
    fun getPlaylistTracks(@Query("id") playlistId: String): Call<PlaylistTracks>
    @GET("track_lyrics/{trackId}")
    fun getTrackLyrics(@Path("trackId") trackId: String): Call<Lyrics>
}


