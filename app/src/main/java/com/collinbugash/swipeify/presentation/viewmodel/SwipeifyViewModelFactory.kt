package com.collinbugash.swipeify.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SwipeifyViewModelFactory() : ViewModelProvider.NewInstanceFactory(){
    companion object{
        private const val LOG_TAG = "448.SwipeifyViewModelFactory"
    }

    fun getViewModelClass() = SwipeifyViewModel::class.java

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        Log.d(LOG_TAG, "create() called")

        if(modelClass.isAssignableFrom(getViewModelClass())) {
            Log.d(LOG_TAG, "creating ViewModel: ${getViewModelClass()}")

            return modelClass
                .getConstructor()
                .newInstance()
        }
        Log.e(LOG_TAG, "Unknown ViewModel: $modelClass")
        throw IllegalArgumentException("Unknown ViewModel")
    }
}