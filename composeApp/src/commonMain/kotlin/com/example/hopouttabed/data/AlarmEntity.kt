package com.example.hopouttabed.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarm")
data class AlarmEntity(
    @PrimaryKey val uuid: String,
    val hour: Int,
    val minute: Int,
    val enabled: Boolean,
    val disabledMinutes: Int,
    val activeDays: List<String>,
    val hasVibrate: Boolean,
    val hasSnooze: Boolean,
    val sound: String
)