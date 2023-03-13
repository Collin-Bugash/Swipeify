package com.collinbugash.swipeify.presentation.playlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.collinbugash.swipeify.data.Song
import com.collinbugash.swipeify.presentation.song.SongLiked
import com.collinbugash.swipeify.ui.theme.SwipeifyTheme
import androidx.compose.foundation.lazy.items

@Composable
fun PlaylistScreen(songList : List<Song>){
    Column() {
        Text(text = "Liked Songs")

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(songList) { song ->
                SongLiked(song)
            }
        }
    }

    
}