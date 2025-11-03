package com.example.hopouttabed.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AlarmRepository(
    private val dao: AlarmDao,
    private val dispatcher: CoroutineDispatcher
) {

    fun getAll(): Flow<List<Alarm>> {
        return dao.getAll().map { list -> list.map { it.toDomain() } }
    }

    suspend fun updateAlarmEnabledState(uuid: String, isEnabled: Boolean): Int {
        return dao.updateEnabledState(uuid, isEnabled)
    }

    suspend fun insert(alarm: Alarm) = withContext(dispatcher) {
        dao.insert(alarm.toEntity())
    }

    suspend fun delete(alarm: Alarm) = withContext(dispatcher) {
        dao.delete(alarm.toEntity())
    }
}
