package com.collinbugash.swipeify.presentation.navigation.specs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.collinbugash.swipeify.presentation.home.HomeScreen
import com.collinbugash.swipeify.presentation.settings.SettingsScreen
import com.collinbugash.swipeify.presentation.viewmodel.SwipeifyViewModel
import kotlinx.coroutines.CoroutineScope

object SettingsScreenSpec : IScreenSpec{
    override val route: String
        get() = "settings"


    @Composable
    override fun Content(swipeifyViewModel : SwipeifyViewModel,
                         navController: NavController,
                         coroutineScope: CoroutineScope
    ){
        val genresSelected = swipeifyViewModel.genresSelectedState.collectAsState()
        val playlists = swipeifyViewModel.playlists
        SettingsScreen(
            genresSelected = genresSelected.value,
            playlists = playlists,
            removeAllGenres = { swipeifyViewModel.removeAllGenres() },
            toggleGenreSelected = { genre -> swipeifyViewModel.toggleGenreSelected(genre) }
        )
    }
}