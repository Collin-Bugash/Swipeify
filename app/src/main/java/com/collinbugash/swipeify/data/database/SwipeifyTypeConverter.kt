package com.collinbugash.swipeify.data.database

import androidx.room.TypeConverter
import com.collinbugash.swipeify.data.types.Album
import com.collinbugash.swipeify.data.types.Artist
import com.google.gson.Gson

class SwipeifyTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun albumToString(album: Album): String {
        return gson.toJson(album)
    }

    @TypeConverter
    fun stringToAlbum(json: String): Album {
        return gson.fromJson(json, Album::class.java)
    }

    @TypeConverter
    fun artistToString(artist: Artist): String {
        return gson.toJson(artist)
    }

    @TypeConverter
    fun stringToArtist(json: String): Artist {
        return gson.fromJson(json, Artist::class.java)
    }

}