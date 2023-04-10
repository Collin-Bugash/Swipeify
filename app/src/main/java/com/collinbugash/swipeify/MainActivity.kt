package com.collinbugash.swipeify

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.collinbugash.swipeify.presentation.navigation.SwipeifyBottomBar
import com.collinbugash.swipeify.presentation.navigation.SwipeifyNavHost
import com.collinbugash.swipeify.presentation.viewmodel.SwipeifyViewModel
import com.collinbugash.swipeify.presentation.viewmodel.SwipeifyViewModelFactory
import com.collinbugash.swipeify.ui.theme.SwipeifyTheme


class MainActivity : ComponentActivity() {
    companion object{
        private const val LOG_TAG = "448.MainActivity"
    }

    private lateinit var mSwipeifyViewModel : SwipeifyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(LOG_TAG, "onCreate() called")

        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "super.onCreate() called")

        val factory = SwipeifyViewModelFactory(this)
        mSwipeifyViewModel = ViewModelProvider(this,factory)[factory.getViewModelClass()]

        setContent {
            MainActivityContent(swipeifyViewModel = mSwipeifyViewModel)
        }
    }
}

@Composable
private fun MainActivityContent(swipeifyViewModel: SwipeifyViewModel){
    val navController = rememberNavController()
    val context = LocalContext.current








    SwipeifyTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(bottomBar = { SwipeifyBottomBar(
                navHostController = navController,
                swipeifyViewModel = swipeifyViewModel,
                context = context
            )}) {
                contentPadding ->  SwipeifyNavHost(modifier = Modifier.padding(contentPadding), navController = navController, swipeifyViewModel = swipeifyViewModel)
            }
        }
    }
}
