package com.collinbugash.swipeify.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.*
import com.collinbugash.swipeify.api.TrackFetcher
import com.collinbugash.swipeify.presentation.home.HomeScreen
import com.collinbugash.swipeify.presentation.lyrics.LyricsScreen
import com.collinbugash.swipeify.presentation.viewmodel.SwipeifyViewModel
import kotlinx.coroutines.CoroutineScope

object LyricsScreenSpec : IScreenSpec{

    private const val SONG_NAME = "song_name"
    override val route: String
        get() = "lyric"


    @Composable
    override fun Content(swipeifyViewModel : SwipeifyViewModel,
                         navController: NavController,
                         coroutineScope: CoroutineScope
    ){
        val swipeifyFetchr = remember { TrackFetcher() }
        val lyricsState = swipeifyFetchr.trackLyricsState.collectAsState(context = coroutineScope.coroutineContext)

        LyricsScreen(
            backButtonPressed = { navController.navigate("home") },
            swipeifyViewModel = swipeifyViewModel,
            currentLyrics = lyricsState.value
        )
    }
}