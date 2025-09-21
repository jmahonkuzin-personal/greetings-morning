package com.example.hopouttabed.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AlarmRepository(
    private val dao: AlarmDao,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getAll(): List<Alarm> = withContext(dispatcher) {
        dao.getAll().map { it.toDomain() }
    }

    suspend fun insert(alarm: Alarm) = withContext(dispatcher) {
        dao.insert(alarm.toEntity())
    }

    suspend fun delete(alarm: Alarm) = withContext(dispatcher) {
        dao.delete(alarm.toEntity())
    }
}
