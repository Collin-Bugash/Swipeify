package com.collinbugash.swipeify.data

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

@Composable
fun getSongURL(songID: Int) {
    val context = LocalContext.current
    // testing a GET request
    val queue = Volley.newRequestQueue(context)
    val url: String = "http://api.music-story.com/en/lyric/${songID}"
    // Request a string response from the provided URL.
    val stringReq = StringRequest(
        Request.Method.GET, url,
        { response ->
            val jsonResponse = JSONObject(response)
            // pull URL for 30 second clip of song
            val songURL = jsonResponse.getString("preview")
            Log.d("API", songURL)
        },
        { Log.d("API", "that didn't work") })
    queue.add(stringReq)
}