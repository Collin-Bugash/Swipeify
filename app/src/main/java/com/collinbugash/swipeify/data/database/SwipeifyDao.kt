package com.collinbugash.swipeify.data.database

import androidx.room.*
import com.collinbugash.swipeify.data.types.Track
import kotlinx.coroutines.flow.Flow

@Dao
interface SwipeifyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTrack(track: Track)
    @Query("SELECT * FROM track WHERE genre=(:genre)")
    fun getTracksByGenre(genre: String): Flow<List<Track>>
    @Query("SELECT * FROM track WHERE id=(:id)")
    suspend fun getTrackById(id: Int): Track?
    @Delete
    suspend fun deleteTrack(track: Track)
}