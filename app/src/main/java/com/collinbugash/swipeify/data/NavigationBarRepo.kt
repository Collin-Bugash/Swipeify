package com.collinbugash.swipeify.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings

object NavigationBarRepo {
    val bottomNavItems = listOf(
        NavigationBarItem(
            name = "Home",
            route = "home",
            icon = Icons.Rounded.Home,
        ),
        NavigationBarItem(
            name = "Share",
            route = "playlist",
            icon = Icons.Rounded.AddCircle,
        ),
        NavigationBarItem(
            name = "Settings",
            route = "settings",
            icon = Icons.Rounded.Settings,
        ),
    )
}