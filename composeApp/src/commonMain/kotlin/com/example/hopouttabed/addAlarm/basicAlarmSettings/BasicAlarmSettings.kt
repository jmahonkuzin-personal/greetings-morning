package com.example.hopouttabed.addAlarm.basicAlarmSettings

import androidx.compose.runtime.Composable
import com.example.hopouttabed.addAlarm.viewModel.BasicAlarmSettingsCallbacks
import com.example.hopouttabed.addAlarm.viewModel.BasicAlarmSettingsUiState

@Composable
fun BasicAlarmSettings(
    basicSettingsUiState: BasicAlarmSettingsUiState,
    callbacks: BasicAlarmSettingsCallbacks,
) {
    val vibrateEnabled = basicSettingsUiState.hasVibrate
    val snoozeEnabled = basicSettingsUiState.hasSnooze
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