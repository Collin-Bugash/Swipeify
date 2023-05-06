package com.collinbugash.swipeify.presentation.playlist

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.collinbugash.swipeify.data.db.Track

@Composable
fun SongLiked(track : Track){
    Text(text = track.name)
}