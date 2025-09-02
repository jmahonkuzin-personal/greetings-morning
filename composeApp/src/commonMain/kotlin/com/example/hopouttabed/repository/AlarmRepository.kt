package com.example.hopouttabed.repository

// commonMain
interface AlarmRepositoryInterface {
    suspend fun getAll(): List<Alarm>
    suspend fun insert(alarm: Alarm)
    suspend fun delete(alarm: Alarm)
}