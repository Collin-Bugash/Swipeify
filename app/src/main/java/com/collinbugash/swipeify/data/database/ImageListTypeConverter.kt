package com.collinbugash.swipeify.data.database

import androidx.room.TypeConverter
import com.collinbugash.swipeify.data.db.Image
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ImageListTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): List<Image> {
        val listType = object : TypeToken<List<Image>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(images: List<Image>): String {
        return gson.toJson(images)
    }
}

