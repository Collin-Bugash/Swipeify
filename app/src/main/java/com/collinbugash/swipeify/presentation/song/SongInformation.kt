package com.collinbugash.swipeify.presentation.song

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.collinbugash.swipeify.data.db.Track

@Composable
fun SongInformation(currentSong: Track?){
    if(currentSong != null) {
        Text(text = currentSong.name, modifier = Modifier.padding(0.dp), style = MaterialTheme.typography.bodyLarge)

        Text(text = getArtists(currentSong), modifier = Modifier.padding(10.dp), style = MaterialTheme.typography.bodyLarge)

        Text(text = currentSong.genre, modifier = Modifier.padding(0.dp), style = MaterialTheme.typography.bodyLarge)
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