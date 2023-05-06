package com.collinbugash.swipeify.presentation.playlist

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.collinbugash.swipeify.data.db.Track

@Composable
fun SongLiked(track : Track){
    Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(vertical = 10.dp)) {
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