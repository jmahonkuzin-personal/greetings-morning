package com.example.hopouttabed

import kotlinx.datetime.LocalDate

interface AlarmScheduler {
    fun schedule(alarmId: String, epochMillis: Long, title: String, body: String)
    fun cancel(alarmId: String)
    fun cancelAll()
}

expect fun provideAlarmScheduler(): AlarmScheduler


//@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
//expect class AlarmSchedulerProvider() { //the parentheses () after the class name declares a default constructor.
//    fun getAlarmScheduler(): AlarmScheduler
//}