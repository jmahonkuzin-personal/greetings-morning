package com.example.hopouttabed.addAlarm.time

import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration

fun timeUntilFun(nextTime: LocalTime, zone: TimeZone = TimeZone.currentSystemDefault()): Duration {
    val nowInstant = Clock.System.now()
    val today = nowInstant.toLocalDateTime(zone).date

    // candidate: today at `nextTime`
    val candidate = LocalDateTime(today, nextTime).toInstant(zone)

    // if candidate already passed, move to tomorrow
    val target = if (candidate <= nowInstant) {
        LocalDateTime(today.plus(DatePeriod(days = 1)), nextTime).toInstant(zone)
    } else candidate

    return target - nowInstant
}