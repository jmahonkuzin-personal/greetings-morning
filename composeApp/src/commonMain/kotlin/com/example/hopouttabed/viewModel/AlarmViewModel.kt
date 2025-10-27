package com.example.hopouttabed.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hopouttabed.dashboard.viewModel.AlarmDayOfWeek
import com.example.hopouttabed.data.Alarm
import com.example.hopouttabed.data.AlarmRepository
import com.example.hopouttabed.data.DI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalTime

data class AlarmUiState(
    val time: LocalTime = LocalTime(7, 30, 0, 0),
    val disabledMinutes: Int = 0,
    val activeDays: List<String> = mutableListOf(),
    val hasVibrate: Boolean = true,
    val hasSnooze: Boolean = true,
    val sound: AlarmSound = AlarmSound.Beacon,
    val isEnabled: Boolean = false
)

data class AlarmCallbacks(
    val updateTime: (hour: Int, minute: Int) -> Unit = { _, _ -> },
    val updateDisabledMinutes: (Int) -> Unit = {},
    val updateActiveDays: (List<String>) -> Unit = {},
    val toggleVibrate: (Boolean) -> Unit = {},
    val toggleSnooze: (Boolean) -> Unit = {},
    val updateSound: (AlarmSound) -> Unit = {},
    val toggleIsEnabled: (Boolean) -> Unit = {}
)

class AlarmViewModel(
    private val repository: AlarmRepository = DI.alarmRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AlarmUiState())
    val uiState: StateFlow<AlarmUiState> = _uiState.asStateFlow()

    fun saveAlarm() {
        viewModelScope.launch {
            val uiState = _uiState.value

            val alarm = Alarm(
                hour = uiState.time.hour,
                minute = uiState.time.minute,
                disabledMinutes = uiState.disabledMinutes,
                activeDays = uiState.activeDays.mapTo(mutableSetOf()) { AlarmDayOfWeek.valueOf(it) },
                hasVibrate = uiState.hasVibrate,
                hasSnooze = uiState.hasSnooze,
                sound = uiState.sound.toString(),
                enabled = uiState.isEnabled
            )
            repository.insert(alarm) // or alarm.toEntity() if your DB uses AlarmEntity
        }
    }

    suspend fun deleteAlarm(alarm: Alarm) {
        viewModelScope.launch {
            repository.delete(alarm)
        }
    }

    fun updateTime(hour: Int, minute: Int) {
        _uiState.update { current ->
            current.copy(
                time = LocalTime(hour, minute)
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

    fun updateActiveDays(newActiveDays: List<String>) {
        _uiState.update { current ->
            current.copy(
                activeDays = newActiveDays
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

    fun toggleIsEnabled(@Suppress("UNUSED_PARAMETER") value: Boolean) {
        _uiState.update { current ->
            current.copy(
                isEnabled = !current.isEnabled
            )
        }
    }
}