package com.example.hopouttabed.addAlarm.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalTime

data class AlarmUiState(
    val time: LocalTime = LocalTime(7, 30, 0, 0),
    val disabledMinutes: Int = 0,
    val allowedAppsDuringDisable: List<String> = mutableListOf(),
    val hasVibrate: Boolean = true,
    val hasSnooze: Boolean = true,
    val sound: AlarmSound = AlarmSound.Beacon,
    val requireJournal: Boolean = true
)

data class AlarmCallbacks(
    val updateTime: (LocalTime) -> Unit = {},
    val updateDisabledMinutes: (Int) -> Unit = {},
    val updateAllowedAppsDuringDisable: (List<String>) -> Unit = {},
    val toggleVibrate: (Boolean) -> Unit = {},
    val toggleSnooze: (Boolean) -> Unit = {},
    val updateSound: (AlarmSound) -> Unit = {},
    val toggleRequireJournal: (Boolean) -> Unit = {}
)

class AlarmViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AlarmUiState())
    val uiState: StateFlow<AlarmUiState> = _uiState.asStateFlow()

    fun updateTime(newTime: LocalTime) {
        _uiState.update { current ->
            current.copy(
                time = newTime
            )
        }
    }

    fun updateDisabledMinutes(disabledMinutes: Int) {
        _uiState.update { current ->
            current.copy(
                disabledMinutes = disabledMinutes
            )
        }
    }

    fun updateAllowedAppsDuringDisable(newAllowedApps: List<String>) {
        _uiState.update { current ->
            current.copy(
                allowedAppsDuringDisable = newAllowedApps
            )
        }
    }

    fun updateSound(sound: AlarmSound) {
        _uiState.update { current ->
            current.copy(
                sound = sound
            )
        }
    }

    fun toggleVibrate(@Suppress("UNUSED_PARAMETER") value: Boolean) {
        _uiState.update { current ->
            current.copy(
                hasVibrate = !current.hasVibrate
            )
        }
    }

    fun toggleSnooze(@Suppress("UNUSED_PARAMETER") value: Boolean) {
        _uiState.update { current ->
            current.copy(
                hasSnooze = !current.hasSnooze
            )
        }
    }
}