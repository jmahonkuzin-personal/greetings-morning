package com.example.hopouttabed.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class AlarmRepository(
    private val dao: AlarmDao,
    private val dispatcher: CoroutineDispatcher
) {
    fun getAll(): Flow<List<Alarm>> = flow {
        // Fetches data in a background thread
        withContext(dispatcher) {
            emit(dao.getAll().map { it.toDomain() })
        }
    }

    suspend fun updateAlarmEnabledState(uuid: String, isEnabled: Boolean) {
        dao.updateEnabledState(uuid, isEnabled)
    }

    suspend fun insert(alarm: Alarm) = withContext(dispatcher) {
        dao.insert(alarm.toEntity())
    }

    suspend fun delete(alarm: Alarm) = withContext(dispatcher) {
        dao.delete(alarm.toEntity())
    }
}
