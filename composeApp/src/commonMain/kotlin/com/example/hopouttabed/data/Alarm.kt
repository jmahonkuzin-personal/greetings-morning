package com.example.hopouttabed.data

import com.example.hopouttabed.dashboard.viewModel.AlarmDayOfWeek
import com.example.hopouttabed.randomUUID

data class Alarm(
    val uuid: String = randomUUID(),
    val hour: Int,
    val minute: Int,
    val enabled: Boolean,
    val disabledMinutes: Int,
    val activeDays: Set<AlarmDayOfWeek> = setOf(AlarmDayOfWeek.Monday, AlarmDayOfWeek.Tuesday, AlarmDayOfWeek.Wednesday),
    val hasVibrate: Boolean,
    val hasSnooze: Boolean,
    val sound: String,
    val requireJournal: Boolean
)

fun AlarmEntity.toDomain(): Alarm = Alarm(
    uuid,
    hour,
    minute,
    enabled,
    disabledMinutes,
    activeDays = activeDays.mapTo(mutableSetOf()) { AlarmDayOfWeek.valueOf(it) },
    hasVibrate,
    hasSnooze,
    sound,
    requireJournal
)

fun Alarm.toEntity(): AlarmEntity = AlarmEntity(
    uuid,
    hour,
    minute,
    enabled,
    disabledMinutes,
    activeDays = activeDays.map { it.name }.toList(),
    hasVibrate,
    hasSnooze,
    sound,
    requireJournal
)