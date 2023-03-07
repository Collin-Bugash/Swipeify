package com.collinbugash.swipeify.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface SwipeifyService {
    @GET("track/{trackId}")
    fun getTrack(@Path("trackId") trackId: String): Call<Track>
}


