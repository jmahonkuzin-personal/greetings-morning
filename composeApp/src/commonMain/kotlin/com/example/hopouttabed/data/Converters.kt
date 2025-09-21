package com.example.hopouttabed.data

import androidx.room.TypeConverter

class Converters {

    // List<String>
    @TypeConverter
    fun fromList(list: List<String>): String = list.joinToString(",")

    @TypeConverter
    fun toList(data: String): List<String> =
        if (data.isBlank()) emptyList() else data.split(",")

    // Add more converters below if needed
}