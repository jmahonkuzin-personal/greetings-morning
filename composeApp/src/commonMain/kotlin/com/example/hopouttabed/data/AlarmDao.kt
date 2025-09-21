package com.example.hopouttabed.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarm")
    suspend fun getAll(): List<AlarmEntity>

    @Insert
    suspend fun insert(vararg alarmEntity: AlarmEntity)

    @Delete
    suspend fun delete(alarmEntity: AlarmEntity)
}