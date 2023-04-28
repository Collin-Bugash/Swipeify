package com.collinbugash.swipeify.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.collinbugash.swipeify.data.db.Track
import kotlinx.coroutines.flow.Flow

@Dao
interface SwipeifyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTrack(track: Track)
    @Query("SELECT * FROM track WHERE genre=(:genre)")
    fun getTracksByGenre(genre: String): Flow<List<Track>>
    @Query("SELECT * FROM track WHERE genre IN (:genres) AND favorite = 0 ORDER BY RANDOM() LIMIT 1")
    fun getRandomTrackByGenres(genres: List<String>): Track?
    @Query("SELECT * FROM track WHERE favorite = 1")
    fun getFavoritedSongs(): List<Track>
    @Query("SELECT * FROM track WHERE id=(:id)")
    suspend fun getTrackById(id: Int): Track?
    @Delete
    suspend fun deleteTrack(track: Track)
    @Update
    suspend fun updateTrackFavorite(track: Track)

}
