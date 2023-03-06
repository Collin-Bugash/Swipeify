package com.collinbugash.swipeify.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.collinbugash.swipeify.presentation.song.SongButtons
import com.collinbugash.swipeify.presentation.song.SongImage
import com.collinbugash.swipeify.presentation.song.SongInformation
import com.collinbugash.swipeify.ui.theme.DarkBlack1

@Composable
fun HomeScreen(){
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        SongImage()

        SongInformation()

        SongButtons()
    }

}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}