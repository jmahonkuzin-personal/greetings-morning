package com.example.hopouttabed.data

import javax.inject.Inject

class AlarmRepository @Inject constructor(
    private val dao: AlarmDao
) : AlarmRepositoryInterface {

    override suspend fun getAll(): List<Alarm> {
        return dao.getAll()
    }

    override suspend fun insert(alarm: Alarm) {
        dao.insert(alarm)
    }

    override suspend fun delete(alarm: Alarm) {
        dao.delete(alarm)
    }
}