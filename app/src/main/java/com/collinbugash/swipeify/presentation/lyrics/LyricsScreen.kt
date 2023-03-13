package com.collinbugash.swipeify.presentation.lyrics

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.collinbugash.swipeify.presentation.song.SongButtons

@Composable
fun LyricsScreen(backButtonPressed:() -> Unit){
    val borderWidth = 3.dp

    Column {
        Row(modifier = Modifier.fillMaxWidth().padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = "Song Name")

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
        
        Text(text = "Lyrics are here")

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
            SongButtons()
        }


    }

}