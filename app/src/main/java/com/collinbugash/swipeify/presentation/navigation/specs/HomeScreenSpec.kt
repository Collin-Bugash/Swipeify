package com.collinbugash.swipeify.presentation.navigation.specs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.collinbugash.swipeify.presentation.home.HomeScreen
import com.collinbugash.swipeify.presentation.viewmodel.SwipeifyViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.collinbugash.swipeify.api.TrackFetcher
import kotlinx.coroutines.CoroutineScope

object HomeScreenSpec : IScreenSpec {
    override val route: String = "home"


    @Composable
    override fun Content(swipeifyViewModel : SwipeifyViewModel,
                         navController: NavController,
                         coroutineScope: CoroutineScope
    ){
        val currentSong = swipeifyViewModel.currentSong.collectAsState()
        val playIconState = swipeifyViewModel.playIconState.collectAsState()

        HomeScreen(
            onLyricButtonClicked = {
                navController.navigate("lyric") },
            currentSong = currentSong.value,
            dislikeSong = { swipeifyViewModel.dislikedSong() },
            likeSong = { swipeifyViewModel.likedSong() },
            playIconState = playIconState.value,
            updateIconState = { swipeifyViewModel.updateIconState() }
        )
    }
}