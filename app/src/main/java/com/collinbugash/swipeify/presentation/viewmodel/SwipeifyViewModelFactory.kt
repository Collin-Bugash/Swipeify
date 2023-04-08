package com.collinbugash.swipeify.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.collinbugash.swipeify.data.SwipeifyRepo

class SwipeifyViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory(){
    companion object{
        private const val LOG_TAG = "448.SwipeifyViewModelFactory"
    }

    fun getViewModelClass() = SwipeifyViewModel::class.java

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        Log.d(LOG_TAG, "create() called")

        if(modelClass.isAssignableFrom(getViewModelClass())) {
            Log.d(LOG_TAG, "creating ViewModel: ${getViewModelClass()}")
            val repoInstance = SwipeifyRepo.getInstance(context)
            return modelClass
                .getConstructor(SwipeifyRepo::class.java)
                .newInstance(repoInstance)
        }
        Log.e(LOG_TAG, "Unknown ViewModel: $modelClass")
        throw IllegalArgumentException("Unknown ViewModel")
    }
}