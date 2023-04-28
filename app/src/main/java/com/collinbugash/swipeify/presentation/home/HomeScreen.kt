package com.collinbugash.swipeify.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.collinbugash.swipeify.data.db.Track
import com.collinbugash.swipeify.presentation.song.SongButtons
import com.collinbugash.swipeify.presentation.song.SongImage
import com.collinbugash.swipeify.presentation.song.SongInformation

@Composable
fun HomeScreen(
    onLyricButtonClicked:() -> Unit,
    currentSong: Track?,
    dislikeSong: () -> Unit,
    likeSong: () -> Unit,
    playIconState: Boolean,
    updateIconState: () -> Unit
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.background
        )
    ) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth().fillMaxHeight(0.80f)
                    .padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item { SongImage(onLyricButtonClicked = { onLyricButtonClicked()/*TODO FILL IN THIS METHOD LATER TO SHOW LYRICS*/}, currentSong)

                     }

                item {
                    SongInformation(currentSong)
                }
            }

            SongButtons(dislikeSong = dislikeSong, likeSong = likeSong, playIconState = playIconState, updateIconState = updateIconState)
        }
    }


}

@Composable
@Preview
fun HomeScreenPreview() {
    //Fix preview TODO
//    HomeScreen({})
}