package com.collinbugash.swipeify.presentation.song

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.collinbugash.swipeify.api.TrackFetcher


@Composable
fun SongButtons(){
    val borderWidth = 3.dp
    val context = LocalContext.current;

    // test song
    val trackFetcher = TrackFetcher()
    val trackState = trackFetcher.trackState.collectAsState()
    trackFetcher.getTrack("916424")

    Row(modifier = Modifier
        .padding(vertical = 20.dp)
        .fillMaxWidth(0.75f)
        .fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
        IconButton(
            onClick = { Toast.makeText(context, "User disliked song", Toast.LENGTH_SHORT).show() },
            modifier = Modifier
                .size(40.dp)
                .border(
                    BorderStroke(borderWidth, Color.White),
                    CircleShape
                )
                .padding(borderWidth)
                .clip(CircleShape)
        ) {
            Icon(
                modifier = Modifier.graphicsLayer { rotationZ = 180f },
                imageVector = Icons.Filled.ThumbUp,
                contentDescription = "Dislike"
            )
        }

        IconButton(
            onClick =
            {
                 Toast.makeText(context, "User played song", Toast.LENGTH_SHORT).show()
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
            },
            modifier = Modifier
                .size(40.dp)
                .border(
                    BorderStroke(borderWidth, Color.White),
                    CircleShape
                )
                .padding(borderWidth)
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "Play",
            )
        }

        IconButton(
            onClick = { Toast.makeText(context, "User liked song", Toast.LENGTH_SHORT).show() },
            modifier = Modifier
                .size(40.dp)
                .border(
                    BorderStroke(borderWidth, Color.White),
                    CircleShape
                )
                .padding(borderWidth)
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Like"
            )
        }


    }
}