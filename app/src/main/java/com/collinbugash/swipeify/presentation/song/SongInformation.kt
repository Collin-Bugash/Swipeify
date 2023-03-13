package com.collinbugash.swipeify.presentation.song

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SongInformation(){
    Text(text = "Song Name", modifier = Modifier.padding(0.dp), style = MaterialTheme.typography.bodyLarge)

    Text(text = "Artist Name", modifier = Modifier.padding(10.dp), style = MaterialTheme.typography.bodyLarge)

    Text(text = "Genre", modifier = Modifier.padding(0.dp), style = MaterialTheme.typography.bodyLarge)

}