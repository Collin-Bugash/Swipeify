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
import com.collinbugash.swipeify.data.db.Track
import com.collinbugash.swipeify.presentation.navigation.SwipeifyBottomBar
import com.collinbugash.swipeify.presentation.navigation.SwipeifyNavHost
import com.collinbugash.swipeify.presentation.viewmodel.SwipeifyViewModel
import com.collinbugash.swipeify.presentation.viewmodel.SwipeifyViewModelFactory
import com.collinbugash.swipeify.ui.theme.SwipeifyTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    companion object{
        private const val LOG_TAG = "448.MainActivity"
    }

    private val PREFS_NAME = "MyPrefsFile"
    private val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"

    private lateinit var mSwipeifyViewModel : SwipeifyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(LOG_TAG, "onCreate() called")

        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "super.onCreate() called")

        val factory = SwipeifyViewModelFactory(this)
        mSwipeifyViewModel = ViewModelProvider(this,factory)[factory.getViewModelClass()]

        // Check if app is opened for the first time
        val sharedPreferences = getSharedPreferences(PREFS_NAME, 0)
        val isFirstTimeLaunch = sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, true)
        if (isFirstTimeLaunch) {
            // Insert songs from playlists into db
            val coroutineScope: CoroutineScope = GlobalScope
            coroutineScope.launch {
                mSwipeifyViewModel.addPlaylists()
            }

            // Set the flag to false so this code won't run again
            val editor = sharedPreferences.edit()
            editor.putBoolean(IS_FIRST_TIME_LAUNCH, false)
            editor.apply()
        }

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
