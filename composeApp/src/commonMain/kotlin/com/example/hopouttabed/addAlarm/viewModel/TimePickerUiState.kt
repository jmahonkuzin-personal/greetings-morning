package com.example.hopouttabed.addAlarm.viewModel

import kotlinx.datetime.LocalTime

data class TimePickerUiState(
    val time: LocalTime = LocalTime(7, 59, 0, 0),
    val is24hour: Boolean = false,
    val isAfternoon: Boolean = false
)

enum class Meridiem {
    AM, PM
}