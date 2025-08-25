package com.example.hopouttabed.data

import androidx.room.TypeConverter
import java.util.*

class Converters {
    // UUID
    @TypeConverter
    fun fromUUID(uuid: UUID): String = uuid.toString()

    @TypeConverter
    fun toUUID(uuid: String): UUID = uuid.let { UUID.fromString(it) }

    // List<String>
    @TypeConverter
    fun fromList(list: List<String>): String = list.joinToString(",")

    @TypeConverter
    fun toList(data: String): List<String> =
        if (data.isBlank()) emptyList() else data.split(",")

    // Add more converters below if needed
}