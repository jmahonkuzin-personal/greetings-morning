package com.example.hopouttabed.data

interface AlarmRepositoryInterface {
    suspend fun getAll() : List<Alarm>
    suspend fun insert(alarm: Alarm)
    suspend fun delete(alarm: Alarm)
}