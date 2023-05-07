package com.collinbugash.swipeify.presentation.playlist

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.collinbugash.swipeify.data.db.Track
import com.collinbugash.swipeify.presentation.song.SongImage
import com.collinbugash.swipeify.presentation.song.SongInformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongLiked(track : Track, removeSong:(Track) -> Unit){
    val currentItem = rememberUpdatedState(newValue = track)
    val dismissState = rememberDismissState(
        initialValue = DismissValue.Default,
        confirmValueChange = {
            if(it == DismissValue.DismissedToStart){
                Log.d("SWIPE INFO:", "DISLIKED SONG")
                removeSong(currentItem.value)
            }
            false
        }
    )

    SwipeToDismiss(
        state = dismissState,
        /***  create dismiss alert Background */
        /***  create dismiss alert Background */
        background = {
            SwipeBackground(dismissState)
        },
        /**** Dismiss Content */
        /**** Dismiss Content */
        dismissContent = {

            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(vertical = 10.dp).fillMaxWidth()) {
                val imageUrl = track?.album?.images?.get(0)?.url

                Row() {
                    AsyncImage(modifier = Modifier
                        .width(75.dp)
                        .height(75.dp),
                        contentDescription = "Album Image",
                        model = imageUrl
                    )
                    Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(start = 10.dp)) {
                        Text(text = track.name, modifier = Modifier.padding(bottom = 10.dp))
                        Text(text = getArtists(track))

                    }
                }

            }

        },
        /*** Set Direction to dismiss */
        /*** Set Direction to dismiss */
        directions = setOf(DismissDirection.EndToStart),
    )

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SwipeBackground(dismissState: DismissState) {
    val direction = dismissState.dismissDirection ?: return

    val color by animateColorAsState(
        when (dismissState.targetValue) {
            DismissValue.Default -> Color.LightGray
            DismissValue.DismissedToEnd -> Color.Green
            DismissValue.DismissedToStart -> Color.Red
        }
    )
    val alignment = when (direction) {
        DismissDirection.StartToEnd -> Alignment.CenterStart
        DismissDirection.EndToStart -> Alignment.CenterEnd
    }
    val icon = when (direction) {
        DismissDirection.StartToEnd -> Icons.Default.Done
        DismissDirection.EndToStart -> Icons.Default.Delete
    }
    val scale by animateFloatAsState(
        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(color)
            .padding(horizontal = 20.dp),
        contentAlignment = alignment
    ) {
        Icon(
            icon,
            contentDescription = "Localized description",
            modifier = Modifier.scale(scale)
        )
    }
}

fun getArtists(currentSong: Track): String {
    val artists = currentSong.artists
    val numArtists = artists.size
    var artistsString = ""
    for(artist in artists) {
        if(artists.indexOf(artist) < numArtists - 1) {
            artistsString = artistsString + artist.name + ", "
        } else {
            artistsString += artist.name
        }
        Log.d("ARTISTS", artistsString)
    }
    return artistsString
}