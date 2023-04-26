package com.collinbugash.swipeify.presentation.viewmodel

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.collinbugash.swipeify.data.SwipeifyRepo
import com.collinbugash.swipeify.data.db.Track
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SwipeifyViewModel(private val swipeifyRepo: SwipeifyRepo) : ViewModel() {
    companion object {
        private const val LOG_TAG = "448.SwipeifyViewModel"
    }

    //Variable to hold current song
    private val mCurrentSong: MutableStateFlow<Track?> = MutableStateFlow(null)
    val currentSong: Track?
        get() = mCurrentSong.asStateFlow().value

    //List of liked songs
    private val mLikedSongs: MutableStateFlow<List<Track>> = MutableStateFlow(emptyList())
    val likedSongsState: StateFlow<List<Track>>
        get() = mLikedSongs.asStateFlow()

    // playlist id's that hold songs for each genre, also holds setting if they're enabled / disabled
    // TODO add more genres later
    private val mPlaylists = listOf(
        Pair("37i9dQZF1DX4sWSpwq3LiO?si=956da7b0331a4ef7", "piano"),
        Pair("2UZk7JjJnbTut1w8fqs3JL?si=52b08f117aa44e5f", "pop")
    )
    val playlists: List<Pair<String, String>>
        get() = mPlaylists

    // list of states for genres selected / not selected
    private val mGenresSelected: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val genresSelectedState: StateFlow<List<String>>
        get() = mGenresSelected.asStateFlow()


    fun addTrack(trackToAdd: Track) {
        Log.d(LOG_TAG, "adding track $trackToAdd")
        swipeifyRepo.addTrack(trackToAdd)
    }
    fun addPlaylists(){
        Log.d(LOG_TAG, "adding songs to db")
        swipeifyRepo.addPlaylists(mPlaylists)
    }

    fun getTracksByGenre(genre: String): Flow<List<Track>> = swipeifyRepo.getTracksByGenre(genre)

    // function to toggle genre selected
    fun toggleGenreSelected(genre: String) {
        val currentGenres = mGenresSelected.value.toMutableList()
        if (mGenresSelected.value.contains(genre)) {
            currentGenres.remove(genre)
        } else {
            currentGenres.add(genre)
        }
        mGenresSelected.value = currentGenres
    }
    // function to check if genre is selected
    fun isGenreSelected(genre: String): Boolean {
        return mGenresSelected.value.contains(genre)
    }
    // empty all genres selected
    fun removeAllGenres() {
        mGenresSelected.value = emptyList()
    }

    //TODO: Function for when user disliked song.  Remove song from respective list and move to next song
    fun dislikedSong() {
        //Get the current song
        val newDislikedSong = currentSong
        //Keep user from disliking liked songs (seems like it could cause some bugs), alternatively we can automatically remove from liked songs before disliking
        if(newDislikedSong != null) {
            Log.d(LOG_TAG, "${newDislikedSong.name} was disliked.")
            if (!mLikedSongs.value.contains(newDislikedSong)) {
                //TODO: Function to go to next song goes here
                //TODO: Not sure where to remove song from, uncomment next line to remove song from repo
                //swipeifyRepo.deleteTrack(newDislikedSong)
            }
        }
    }

    //TODO: Function for when user liked song. Remove song from respective list, add to liked list, and move onto next song
    fun likedSong() {
        //Get the current song
        val newLikedSong = currentSong
        if(newLikedSong != null) {
            Log.d(LOG_TAG, "${newLikedSong.name} was liked.")
            //If the current song is not null and is not already liked, add it to the set of liked songs
            if (newLikedSong != null && !mLikedSongs.value.contains(newLikedSong)) {
                mLikedSongs.value = mLikedSongs.value.plus(newLikedSong);

            }
            //If the current song is already liked, remove it from the set of liked songs
            else if (newLikedSong != null) {
                mLikedSongs.value = mLikedSongs.value.minus(newLikedSong)
            }
            //TODO: Function to go to next song goes here
            //TODO: Not sure where to remove song from, uncomment next line to remove song from repo
            //swipeifyRepo.deleteTrack(newDislikedSong)
        }
    }

    init {
        Log.d(LOG_TAG, "View Model Created")
        viewModelScope.launch {

        }
    }
}