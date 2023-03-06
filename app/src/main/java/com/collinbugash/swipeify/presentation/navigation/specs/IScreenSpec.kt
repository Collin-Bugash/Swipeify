package com.collinbugash.swipeify.presentation.navigation.specs

import android.content.Context
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.collinbugash.swipeify.data.NavigationBarRepo
import com.collinbugash.swipeify.presentation.viewmodel.SwipeifyViewModel

sealed interface IScreenSpec {
    val route: String

    @Composable
    fun Content(swipeifyViewModel : SwipeifyViewModel,
                navController: NavController)

    companion object {
        private const val LOG_TAG = "448.IScreenSpec"


        val allScreens = IScreenSpec::class.sealedSubclasses.associate {
            Log.d(LOG_TAG, "allScreens: mapping route \"${it.objectInstance?.route ?: ""}\" to object \"${it.objectInstance}\"")
            it.objectInstance?.route to it.objectInstance
        }

        val root = "swipeify"
        val startDestination = HomeScreenSpec.route

        @Composable
        public fun BottomBar(swipeifyViewModel: SwipeifyViewModel, navController: NavHostController, navBackStackEntry: NavBackStackEntry?, context: Context){
            val route = navBackStackEntry?.destination?.route ?: ""

            allScreens[route]?.BottomAppBarContent(swipeifyViewModel,navController,navBackStackEntry,context)
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun BottomAppBarContent(swipeifyViewModel: SwipeifyViewModel, navController: NavHostController, navBackStackEntry: NavBackStackEntry?, context: Context){
        BottomAppBar(
            actions = {
                NavigationBarRepo.bottomNavItems.forEach{
                    item -> val selected = item.route == navController.currentBackStackEntryAsState().value?.destination?.route

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            println(item.route)
                            navController.navigate(item.route)},
                        label = {
                            Text(text = item.name)
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = "${item.name} Icon"
                            )
                        }
                    )
                }
            }

        )
    }
}


