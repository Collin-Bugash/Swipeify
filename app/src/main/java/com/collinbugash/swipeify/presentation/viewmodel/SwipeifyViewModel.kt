package com.collinbugash.swipeify.presentation.viewmodel

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.collinbugash.swipeify.data.SwipeifyRepo
import com.collinbugash.swipeify.data.db.LyricTrack
import com.collinbugash.swipeify.data.db.Lyrics
import com.collinbugash.swipeify.data.db.Track
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SwipeifyViewModel(private val swipeifyRepo: SwipeifyRepo) : ViewModel() {
    companion object {
        private const val LOG_TAG = "448.SwipeifyViewModel"
    }

    //Variable to hold current song
    private val mCurrentSong: MutableStateFlow<Track?> = MutableStateFlow(null)
    val currentSong: StateFlow<Track?>
        get() = mCurrentSong.asStateFlow()

    //Variable to hold current list of liked songs
    private val mLikedSongs: MutableStateFlow<List<Track>> = MutableStateFlow(emptyList())
    val likedSongs: StateFlow<List<Track>>
        get() = mLikedSongs.asStateFlow()


    // playlist id's that hold songs for each genre, also holds setting if they're enabled / disabled
//    private val mGenres = listOf("piano", "pop", "rock", "R&B", "indie", "country", "jazz", "rap", "EDM")
    private val mGenres = listOf("Piano", "Pop")

    private val mPlaylists = listOf(
        Pair("37i9dQZF1DX4sWSpwq3LiO?si=956da7b0331a4ef7", "Piano"),
        Pair("2UZk7JjJnbTut1w8fqs3JL?si=52b08f117aa44e5f", "Pop"),
//        Pair("6JpQsEf9FrpDAmhKNWIV3B?si=a1897b6322c44d1d", "rock"),
//        Pair("0HFgdtKVI08nyD0rQtLltH?si=fdc03d8aae674f30", "R&B"),
//        Pair("37i9dQZF1EQqkOPvHGajmW?si=dc47d936ec5646c5", "indie"),
//        Pair("37i9dQZF1EQmPV0vrce2QZ?si=77add11b92c642ea", "country"),
//        Pair("37i9dQZF1EQqA6klNdJvwx?si=b0ce17ea984f4138", "jazz"),
//        Pair("37i9dQZF1EIgbjUtLiWmHt?si=a0c611613fbb46de", "rap"),
//        Pair("1lS6v9h4MXOw6f6y8MkS8w?si=b6bf2968d9124b0e", "EDM")
    )
    val playlists: List<Pair<String, String>>
        get() = mPlaylists

    // list of states for genres selected / not selected
    private val mGenresSelected: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val genresSelectedState: StateFlow<List<String>>
        get() = mGenresSelected.asStateFlow()

    private val mPlayIcon: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val playIconState: StateFlow<Boolean>
        get() = mPlayIcon.asStateFlow()

    val playedNewSong: MutableState<Boolean> = mutableStateOf(true)

    private val mediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
    }

    fun pauseMusic() {
        mediaPlayer.pause()
    }

    fun resumeMusic() {
        mediaPlayer.start()
    }

    fun playMusic(url: String) {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(url)
        mediaPlayer.prepare()
        mediaPlayer.start()
    }

    fun addTrack(trackToAdd: Track) {
        Log.d(LOG_TAG, "adding track $trackToAdd")
        swipeifyRepo.addTrack(trackToAdd)
    }
    fun addPlaylists(){
        Log.d(LOG_TAG, "adding songs to db")
        swipeifyRepo.addPlaylists(mPlaylists)
    }

    fun getLyrics() {
        mCurrentSong.value?.let { swipeifyRepo.getLyrics(it.id) }
    }

//    suspend fun getLikedSongs() {
//        Log.d(LOG_TAG, "getting the songs a user has liked")
//        mLikedSongs.value = swipeifyRepo.getFavoritedSongs()
//    }

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

    fun updateIconState() {
        mPlayIcon.value = !mPlayIcon.value
        if(playedNewSong.value) {
            playedNewSong.value = false
            pauseMusic()
        } else {
            if(mPlayIcon.value) {
                pauseMusic()
            } else {
                resumeMusic()
            }
        }
    }

    fun dislikedSong() {
        mediaPlayer.reset()
        playedNewSong.value = true
        mPlayIcon.value = false
        //Get the current song
        val newDislikedSong = currentSong.value
        swipeifyRepo.deleteTrack(newDislikedSong)
        getNextTrack()
    }

    fun likedSong() {
        mediaPlayer.reset()
        playedNewSong.value = true
        mPlayIcon.value = false
        //Get the current song
        val newLikedSong = currentSong.value
        if (newLikedSong != null) {
            newLikedSong.favorite = true
        }
        swipeifyRepo.updateTrack(newLikedSong)
        Log.d("448.LikedSongs", mLikedSongs.value.size.toString())
        getNextTrack()
    }

    fun getNextTrack() {
        Log.d(LOG_TAG, "Getting next song")
        viewModelScope.launch {
            if(genresSelectedState.value.isNotEmpty()) {
                val newSong: Track? = swipeifyRepo.getRandomTrack(genresSelectedState.value)
                mCurrentSong.value = newSong
                Log.d(LOG_TAG, "Current song: ${newSong.toString()}")
            } else {
                val newSong: Track? = swipeifyRepo.getRandomTrack(mGenres)
                mCurrentSong.value = newSong
                Log.d(LOG_TAG, "Current song: ${newSong.toString()}")
            }
            mCurrentSong.value?.let { playMusic(it.preview_url) }
        }
    }

    init {
        Log.d(LOG_TAG, "View Model Created")
        viewModelScope.launch {
            swipeifyRepo.getFavoritedSongs().collect { favoritedList ->
                mLikedSongs.update { favoritedList }
            }
        }
    }
}