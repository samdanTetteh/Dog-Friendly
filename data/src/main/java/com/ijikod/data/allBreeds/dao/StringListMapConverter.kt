package com.ijikod.data.allBreeds.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListMapConverter {
    @TypeConverter
    fun fromString(value: String): Map<String, List<String>> {
        val mapType = object : TypeToken<Map<String, List<String>>>() {
        }.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromStringMap(map: Map<String, List<String>>): String {
        val gson = Gson()
        return gson.toJson(map)
    }
}