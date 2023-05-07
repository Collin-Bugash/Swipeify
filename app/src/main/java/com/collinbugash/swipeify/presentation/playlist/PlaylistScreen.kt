package com.collinbugash.swipeify.presentation.playlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import com.collinbugash.swipeify.data.db.Track

@Composable
fun PlaylistScreen(songList : List<Track>, removeSong:(Track)->Unit){
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Liked Songs")

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(songList) { song ->

                SongLiked(song, removeSong = {track -> removeSong(track)})
            }
        }
    }

    
}