package com.example.hopouttabed.data

import com.example.hopouttabed.randomUUID

data class Alarm(
    val uuid: String = randomUUID(),
    val hour: Int,
    val minute: Int,
    val disabledMinutes: Int,
    val allowedAppsDuringDisable: List<String>,
    val hasVibrate: Boolean,
    val hasSnooze: Boolean,
    val sound: String,
    val requireJournal: Boolean
)

fun AlarmEntity.toDomain(): Alarm = Alarm(
    uuid,
    hour,
    minute,
    disabledMinutes,
    allowedAppsDuringDisable,
    hasVibrate,
    hasSnooze,
    sound,
    requireJournal
)

fun Alarm.toEntity(): AlarmEntity = AlarmEntity(
    uuid,
    hour,
    minute,
    disabledMinutes,
    allowedAppsDuringDisable,
    hasVibrate,
    hasSnooze,
    sound,
    requireJournal
)