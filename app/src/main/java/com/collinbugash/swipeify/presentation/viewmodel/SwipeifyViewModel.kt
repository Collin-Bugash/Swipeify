package com.collinbugash.swipeify.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

class SwipeifyViewModel() : ViewModel() {
    companion object {
        private const val LOG_TAG = "448.SwipeifyViewModel"
    }

    init {
        Log.d(LOG_TAG, "View Model Created")
    }
}