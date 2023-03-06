package com.collinbugash.swipeify.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.collinbugash.swipeify.presentation.navigation.specs.IScreenSpec
import com.collinbugash.swipeify.presentation.viewmodel.SwipeifyViewModel

@Composable
fun SwipeifyBottomBar(navHostController: NavHostController, swipeifyViewModel: SwipeifyViewModel, context: Context){
    val navBackStackEntryState = navHostController.currentBackStackEntryAsState()

    IScreenSpec.BottomBar(swipeifyViewModel,navHostController,navBackStackEntryState.value,context)
}