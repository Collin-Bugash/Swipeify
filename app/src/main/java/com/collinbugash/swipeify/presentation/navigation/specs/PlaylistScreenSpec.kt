package com.collinbugash.swipeify.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.collinbugash.swipeify.presentation.home.HomeScreen
import com.collinbugash.swipeify.presentation.playlist.PlaylistScreen
import com.collinbugash.swipeify.presentation.viewmodel.SwipeifyViewModel
import kotlinx.coroutines.CoroutineScope

object PlaylistScreenSpec : IScreenSpec {
    override val route: String
        get() = "playlist"


    @Composable
    override fun Content(swipeifyViewModel : SwipeifyViewModel,
                         navController: NavController,
                         coroutineScope: CoroutineScope
    ){
        val likedSongs = swipeifyViewModel.likedSongs.collectAsState()
        PlaylistScreen(songList = likedSongs.value, removeSong = {track -> swipeifyViewModel.deleteSong(track)})
    }
}