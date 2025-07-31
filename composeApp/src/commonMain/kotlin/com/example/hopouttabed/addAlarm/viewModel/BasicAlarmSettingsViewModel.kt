package com.example.hopouttabed.addAlarm.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class BasicAlarmSettingsUiState(
    val hasVibrate: Boolean = true,
    val hasSnooze: Boolean = true,
    val sound: String = ""
)

data class BasicAlarmSettingsCallbacks(
    val toggleVibrate: (Boolean) -> Unit,
    val toggleSnooze: (Boolean) -> Unit,
    val updateSound: (String) -> Unit,
)

class BasicAlarmSettingsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(BasicAlarmSettingsUiState())
    val uiState: StateFlow<BasicAlarmSettingsUiState> = _uiState.asStateFlow()

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

    fun updateSound(sound: String) {
        _uiState.update { current ->
            current.copy(
                sound = sound
            )
        }
    }
}