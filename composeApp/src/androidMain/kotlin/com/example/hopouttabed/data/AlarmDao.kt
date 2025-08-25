package com.example.hopouttabed.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarm")
    fun getAll(): List<Alarm>

    @Insert
    fun insert(vararg alarm: Alarm)

    @Delete
    fun delete(alarm: Alarm)
}