package com.example.hopouttabed.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarm")
    fun getAll(): Flow<List<AlarmEntity>>

    @Query("UPDATE alarm SET enabled = :isEnabled WHERE uuid = :uuid")
    suspend fun updateEnabledState(uuid: String, isEnabled: Boolean): Int

    @Insert
    suspend fun insert(vararg alarmEntity: AlarmEntity)

    @Delete
    suspend fun delete(alarmEntity: AlarmEntity)
}