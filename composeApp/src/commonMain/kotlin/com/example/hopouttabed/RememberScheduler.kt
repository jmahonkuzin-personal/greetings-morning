package com.example.hopouttabed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberAlarmScheduler(): AlarmScheduler =
    remember { provideAlarmScheduler() }
