package com.example.hopouttabed.data

import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineDispatcher

expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>
expect val ioDispatcher: CoroutineDispatcher

object DI {
    private var _db: AppDatabase? = null
    private var _alarmRepo: AlarmRepository? = null

    fun init() {
        if (_db == null) {
            val builder = getDatabaseBuilder()
            _db = getRoomDatabase(builder)
        }
    }

    val db: AppDatabase
        get() = _db ?: error("DI not initialized. Call DI.init(...) first.")

    val alarmRepository: AlarmRepository
        get() = _alarmRepo ?: AlarmRepository(db.getDao(), ioDispatcher).also { _alarmRepo = it }
}