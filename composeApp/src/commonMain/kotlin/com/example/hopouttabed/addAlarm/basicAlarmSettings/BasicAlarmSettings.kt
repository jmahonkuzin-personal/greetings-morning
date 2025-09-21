package com.example.hopouttabed.addAlarm.basicAlarmSettings

import androidx.compose.runtime.Composable
import com.example.hopouttabed.viewModel.AlarmCallbacks
import com.example.hopouttabed.viewModel.AlarmUiState

@Composable
fun BasicAlarmSettings(
    alarmUiState: AlarmUiState,
    callbacks: AlarmCallbacks,
) {
    val vibrateEnabled = alarmUiState.hasVibrate
    val snoozeEnabled = alarmUiState.hasSnooze
    SettingsRowToggle(
        label = "Vibrate",
        switchState = vibrateEnabled,
        onToggle = callbacks.toggleVibrate
    )
    SettingsRowToggle(
        label = "Snooze",
        switchState = snoozeEnabled,
        onToggle = callbacks.toggleSnooze
    )
}