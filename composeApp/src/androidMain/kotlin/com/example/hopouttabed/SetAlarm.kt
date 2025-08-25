package com.example.hopouttabed

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

//fun setAlarm(context: Context) {
//    val timeSec = System.currentTimeMillis() + 5000
//    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//    val intent = Intent(context, Alarm::class.java)
//
//// NOTE:
////      FLAG_MUTABLE: Allows the PendingIntent to be modified by the sender.
////      FLAG_IMMUTABLE: Ensures that the PendingIntent cannot be modified after creation.
//    val pendingIntent = PendingIntent.getBroadcast(
//        context,
//        0,
//        intent,
//        PendingIntent.FLAG_MUTABLE
//    )
//    alarmManager.set(AlarmManager.RTC_WAKEUP, timeSec, pendingIntent)
//}