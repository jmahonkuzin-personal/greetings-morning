package com.example.hopouttabed.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "alarm")
data class Alarm(
    @PrimaryKey val uuid: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "hour") val hour: Int, // can change to LocalTime in the future
    @ColumnInfo(name = "minute") val minute: Int,
    @ColumnInfo(name = "missions") val missions: List<String>,
    @ColumnInfo(name = "is_enabled") val isEnabled: Boolean,
    @ColumnInfo(name = "active_days") val activeDays: List<String>
)