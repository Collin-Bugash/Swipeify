package com.collinbugash.swipeify.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.collinbugash.swipeify.data.Track

@Database(entities = [Track::class], version = 2)
abstract class SwipeifyDatabase : RoomDatabase() {
    companion object {
        @Volatile private var INSTANCE: SwipeifyDatabase? = null
        fun getInstance(context: Context): SwipeifyDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(context, SwipeifyDatabase::class.java, "swipeify-database").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
    abstract val swipeifyDao: SwipeifyDao
}