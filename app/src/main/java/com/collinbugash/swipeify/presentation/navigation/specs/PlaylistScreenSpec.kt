package com.collinbugash.swipeify.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.collinbugash.swipeify.presentation.home.HomeScreen
import com.collinbugash.swipeify.presentation.playlist.PlaylistScreen
import com.collinbugash.swipeify.presentation.viewmodel.SwipeifyViewModel

object PlaylistScreenSpec : IScreenSpec {
    override val route: String
        get() = "playlist"


    @Composable
    override fun Content(swipeifyViewModel : SwipeifyViewModel,
                         navController: NavController
    ){
        PlaylistScreen()
    }
}