package com.collinbugash.swipeify.presentation.song

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.collinbugash.swipeify.api.TrackFetcher

@Composable
fun SongButtons(){
    // test song
    val trackFetcher = TrackFetcher()
    val trackState = trackFetcher.trackState.collectAsState()
    trackFetcher.getTrack("3135556")

    Row() {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                modifier = Modifier.graphicsLayer { rotationZ = 180f },
                imageVector = Icons.Filled.ThumbUp,
                contentDescription = "Dislike"
            )
        }

        IconButton(onClick = {
            // test song
            val url = trackState.value?.preview ?: "URL IS NULL" // your URL here
            Log.d("API", url)
            val mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(url)
                prepare() // might take long! (for buffering, etc)
                start()
            }
        }) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "Play",
            )
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Like"
            )
        }


    }
}