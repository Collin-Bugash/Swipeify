package com.collinbugash.swipeify.presentation.navigation.specs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.collinbugash.swipeify.presentation.home.HomeScreen
import com.collinbugash.swipeify.presentation.viewmodel.SwipeifyViewModel

object HomeScreenSpec : IScreenSpec {
    override val route: String = "home"


    @Composable
    override fun Content(swipeifyViewModel : SwipeifyViewModel,
                         navController: NavController
    ){
        println("HOME CALLED")
        HomeScreen()
    }
}