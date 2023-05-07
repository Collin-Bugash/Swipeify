package com.collinbugash.swipeify.presentation.lyrics

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.collinbugash.swipeify.data.db.Lyrics
import com.collinbugash.swipeify.presentation.song.SongButtons
import com.collinbugash.swipeify.presentation.viewmodel.SwipeifyViewModel
import androidx.compose.foundation.lazy.items
import com.collinbugash.swipeify.data.db.LyricTrack

@Composable
fun LyricsScreen(
    backButtonPressed:() -> Unit,
    swipeifyViewModel: SwipeifyViewModel,
    currentLyrics: LyricTrack?){
    val borderWidth = 3.dp

    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween){
            if(swipeifyViewModel.currentSong.value != null)
                Text(modifier = Modifier.fillMaxWidth(0.8f), text = "${swipeifyViewModel.currentSong.value?.name} Lyrics")

            IconButton(
                onClick = { backButtonPressed() },
                modifier = Modifier
                    .size(50.dp)
                    .border(
                        BorderStroke(borderWidth, Color.White),
                        CircleShape
                    )
                    .padding(borderWidth)
                    .clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "Back Button"
                )
            }
        }
        Divider(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp),
            color = MaterialTheme.colorScheme.tertiary
        )
        if(currentLyrics != null){
            Text(text = "Language: ${currentLyrics?.lyrics?.language}")
        }
        //Populating lyrics
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
                .padding(8.dp),
        ) {
            if (currentLyrics != null) {
                items(currentLyrics.lyrics.lines) { line ->
                    Text(text = line.words)
                }
            } else {
                item { Text(text = "No lyrics found") }
            }
        }

        val playIconState = swipeifyViewModel.playIconState.collectAsState()
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
            SongButtons({ swipeifyViewModel.dislikedSong() }, { swipeifyViewModel.likedSong() }, playIconState.value, { swipeifyViewModel.updateIconState() })
        }


    }

}