package com.collinbugash.swipeify.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.collinbugash.swipeify.data.db.Track
import com.collinbugash.swipeify.presentation.song.SongButtons
import com.collinbugash.swipeify.presentation.song.SongImage
import com.collinbugash.swipeify.presentation.song.SongInformation

@OptIn(ExperimentalMaterial3Api::class)
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
            val dismissState = rememberDismissState(
                initialValue = DismissValue.Default,
                confirmValueChange = {
                    if(it == DismissValue.DismissedToEnd){
                        Log.d("SWIPE INFO:", "LIKED SONG")
                        likeSong()
                    } else if(it == DismissValue.DismissedToStart){
                        Log.d("SWIPE INFO:", "DISLIKED SONG")
                        dislikeSong()
                    }
                    false
                }
            )

            SwipeToDismiss(
                state = dismissState,
                /***  create dismiss alert Background */
                background = {
                    val color = when (dismissState.dismissDirection) {
                        DismissDirection.StartToEnd -> Color.Green
                        DismissDirection.EndToStart -> Color.Red
                        null -> Color.Transparent
                    }
                    val direction = dismissState.dismissDirection
                    if (direction == DismissDirection.StartToEnd) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(8.dp)
                        ) {
                            Column(modifier = Modifier.align(Alignment.CenterStart)) {
                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                                Text(
                                    text = "Like!", fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )
                            }

                        }
                    } else if(direction == DismissDirection.EndToStart){
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(8.dp)
                        ) {
                            Column(modifier = Modifier.align(Alignment.CenterEnd)) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                                Spacer(modifier = Modifier.heightIn(5.dp))
                                Text(
                                    text = "Dislike!",
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.LightGray
                                )

                            }
                        }
                    }
                },
                /**** Dismiss Content */
                dismissContent = {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.80f)
                            .padding(8.dp)

                        ,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item { SongImage(onLyricButtonClicked = { onLyricButtonClicked() }, currentSong)

                        }

                        item {
                            SongInformation(currentSong)
                        }
                    }

                },
                /*** Set Direction to dismiss */
                directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
            )
            SongButtons(dislikeSong = dislikeSong, likeSong = likeSong, playIconState = playIconState, updateIconState = updateIconState)

            /*
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.80f)
                    .padding(8.dp)

                ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item { SongImage(onLyricButtonClicked = { onLyricButtonClicked()/*TODO FILL IN THIS METHOD LATER TO SHOW LYRICS*/}, currentSong)

                     }

                item {
                    SongInformation(currentSong)
                }
            }

            SongButtons(dislikeSong = dislikeSong, likeSong = likeSong, playIconState = playIconState, updateIconState = updateIconState)

        */
        }

    }


}