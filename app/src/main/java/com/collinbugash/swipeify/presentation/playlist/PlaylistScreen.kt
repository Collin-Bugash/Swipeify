package com.collinbugash.swipeify.presentation.playlist

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.collinbugash.swipeify.ui.theme.SwipeifyTheme

@Composable
fun PlaylistScreen(){
    println("${lightColorScheme()}")
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text = "Playlist Screen is here",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}