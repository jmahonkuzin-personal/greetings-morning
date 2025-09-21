package com.example.hopouttabed.data

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hopouttabed.AppContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>  {
    val appContext = AppContext.get()
    val dbFile = appContext.getDatabasePath("hopouttabed.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}