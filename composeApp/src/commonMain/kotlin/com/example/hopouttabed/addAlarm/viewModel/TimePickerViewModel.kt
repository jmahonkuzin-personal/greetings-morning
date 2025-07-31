package com.example.hopouttabed.addAlarm.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalTime

class TimePickerViewModel : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            TimePickerUiState(
                time = LocalTime(hour = 7, minute = 30),
                is24hour = false,
                isAfternoon = false
            )
        )
    val uiState: StateFlow<TimePickerUiState> = _uiState.asStateFlow()

    fun updateTime(newTime: LocalTime) {
        _uiState.update { current ->
            current.copy(
                time = newTime,
                isAfternoon = newTime.hour >= 12
            )
        }
    }
}