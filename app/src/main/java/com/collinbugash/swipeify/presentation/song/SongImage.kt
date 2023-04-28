package com.collinbugash.swipeify.presentation.song

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.collinbugash.swipeify.R
import com.collinbugash.swipeify.data.db.Track


@Composable
fun SongImage(onLyricButtonClicked:() -> Unit, currentSong: Track?){
    val borderWidth = 4.dp
    val padding = 10.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val imageUrl = currentSong?.album?.images?.get(0)?.url

    Box(modifier = Modifier.padding(padding).width(screenWidth - padding - padding).height(screenWidth - padding - padding), contentAlignment = Alignment.BottomStart){

        AsyncImage(modifier = Modifier.fillMaxSize(),
            contentDescription = "Album Image",
            model = imageUrl
        )

        IconButton(modifier = Modifier.padding(12.dp), onClick = { onLyricButtonClicked() }) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowUp,
                contentDescription = "Lyrics",
                modifier = Modifier
                    .size(150.dp)
                    .border(
                        BorderStroke(borderWidth, Color.White),
                        CircleShape
                    )
                    .padding(borderWidth)
                    .clip(CircleShape)

            )
        }
    }

}