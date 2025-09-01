package com.example.hopouttabed

import androidx.compose.runtime.Composable
import com.example.hopouttabed.addAlarm.AddAlarmScreen
import com.example.hopouttabed.addAlarm.viewModel.AlarmCallbacks
import com.example.hopouttabed.addAlarm.viewModel.AlarmSound
import com.example.hopouttabed.addAlarm.viewModel.AlarmUiState
import com.example.hopouttabed.theme.WakeUpAppTheme
import kotlinx.datetime.LocalTime
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    // Mock state
    val previewUiState = AlarmUiState(
        time = LocalTime(7, 30),
        disabledMinutes = 15,
        allowedAppsDuringDisable = listOf("Messages", "Spotify"),
        hasVibrate = true,
        hasSnooze = false,
        sound = AlarmSound.Beacon,
        requireJournal = true
    )

    // Preview AlarmCallbacks (all no-ops)
    val previewCallbacks = AlarmCallbacks(
        updateTime = { _, _ -> },
        updateDisabledMinutes = { _ -> },
        updateAllowedAppsDuringDisable = { _ -> },
        toggleVibrate = { _ -> },
        toggleSnooze = { _ -> },
        updateSound = { _ -> },
        toggleRequireJournal = { _ -> }
    )

    WakeUpAppTheme {
        AddAlarmScreen(
            onSave = {},
            onBackConfirmed = {},
            onNavigateToSoundPicker = {},
            alarmUiState = previewUiState,
            alarmCallbacks = previewCallbacks
        )
    }
}