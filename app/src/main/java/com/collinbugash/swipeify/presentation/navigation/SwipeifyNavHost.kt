package com.collinbugash.swipeify.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navigation
import com.collinbugash.swipeify.presentation.navigation.specs.IScreenSpec
import com.collinbugash.swipeify.presentation.viewmodel.SwipeifyViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun SwipeifyNavHost(modifier: Modifier = Modifier, navController: NavHostController, swipeifyViewModel: SwipeifyViewModel, coroutineScope: CoroutineScope) {
    NavHost(modifier = modifier,
        navController = navController, startDestination = IScreenSpec.root
    ) {
        navigation(route = IScreenSpec.root, startDestination = IScreenSpec.startDestination){
            IScreenSpec.allScreens.forEach { (_, screen) ->
                if(screen != null) {
                    composable(
                        route = screen.route,
                    ) { navBackStackEntry ->
                        screen.Content(
                            navController = navController,
                            swipeifyViewModel = swipeifyViewModel,
                            coroutineScope = coroutineScope
                        )
                    }
                }
            }
        }
    }
}