package com.collinbugash.swipeify.data.database

import androidx.room.TypeConverter
import com.collinbugash.swipeify.data.db.Artist
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ArtistListTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): List<Artist> {
        val listType = object : TypeToken<List<Artist>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(artists: List<Artist>): String {
        return gson.toJson(artists)
    }
}

