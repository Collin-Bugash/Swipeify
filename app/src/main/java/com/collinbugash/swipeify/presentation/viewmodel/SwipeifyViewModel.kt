package com.collinbugash.swipeify.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.collinbugash.swipeify.data.SwipeifyRepo
import com.collinbugash.swipeify.data.db.Track
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SwipeifyViewModel(private val swipeifyRepo: SwipeifyRepo) : ViewModel() {
    companion object {
        private const val LOG_TAG = "448.SwipeifyViewModel"
    }

    //Variable to hold current song
    private val mCurrentSong: MutableStateFlow<Track?> = MutableStateFlow(null)
    val currentSong: Track?
        get() = mCurrentSong.asStateFlow().value


    //Variable flags for each genre
    //For piano genre
    private val mPianoGenre: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val pianoGenre: Boolean
        get() = mPianoGenre.asStateFlow().value
    /*This variable keeps track if the piano option has been chosen before.  If it hasn't, then we'll want to
    create a list of only piano songs*/
    private val mPianoFirst: MutableStateFlow<Boolean> = MutableStateFlow(false)
    //List of piano songs
    private val mPianoSongs: MutableStateFlow<List<Track>> = MutableStateFlow(emptyList())


    //For pop genre
    private val mPopGenre: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val popGenre: Boolean
        get() = mPopGenre.asStateFlow().value
    /*This variable keeps track if the piano option has been chosen before.  If it hasn't, then we'll want to
    create a list of only pop songs*/
    private val mPopFirst: MutableStateFlow<Boolean> = MutableStateFlow(false)
    //List of pop songs
    private val mPopSongs: MutableStateFlow<List<Track>> = MutableStateFlow(emptyList())



    //List of liked songs
    private val mLikedSongs: MutableStateFlow<List<Track>> = MutableStateFlow(emptyList())


    fun addTrack(trackToAdd: Track) {
        Log.d(LOG_TAG, "adding track $trackToAdd")
        swipeifyRepo.addTrack(trackToAdd)
    }
    suspend fun addPlaylists(){
        Log.d(LOG_TAG, "adding playlists songs to db")
        swipeifyRepo.addPlaylists()
    }

    fun getTracksByGenre(genre: String): Flow<List<Track>> = swipeifyRepo.getTracksByGenre(genre)

    init {
        Log.d(LOG_TAG, "View Model Created")
    }

    //For when reset button is clicked
    fun resetGenres() {
        mPianoGenre.value = false
        mPopGenre.value = false
    }

    //For when piano button is clicked
    fun pianoGenreSelected() {
        mPianoGenre.value = !(mPianoGenre.value)
        if (mPianoFirst.value == false) {
            mPianoFirst.value = true
            //TODO Get piano songs and populate mPianoSongs
        }

    }

    //For when piano button is clicked
    fun popGenreSelected() {
        mPopGenre.value = !(mPopGenre.value)
        if (mPopFirst.value == false) {
            mPopFirst.value = true
            //TODO Get pop songs and populate mPopSongs
        }
    }

    //TODO: Function for when user disliked song.  Remove song from respective list and move to next song
    fun dislikedSong() {

    }

    //TODO: Function for when user liked song. Remove song from respective list, add to liked list, and move onto next song
    fun likedSong() {

    }
}