package com.example.hopouttabed.dashboard.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DashboardViewModel : ViewModel() {

    private val _alarms = MutableStateFlow<List<AlarmUiModel>>(
        listOf(
            AlarmUiModel("7:30", "AM", listOf("light", "megaphone", "sound"), enabled = true),
            AlarmUiModel("9:00", "AM", listOf("sound"), enabled = true),
            AlarmUiModel("6:00", "AM", listOf("light", "sound"), enabled = false),
            AlarmUiModel("6:30", "AM", listOf("sound"), enabled = false),
        )
    )

    val alarms: StateFlow<List<AlarmUiModel>> = _alarms.asStateFlow()

    fun toggleAlarm(index: Int, enabled: Boolean) {
        _alarms.update { list ->
            list.mapIndexed { i, alarm ->
                if (i == index) alarm.copy(enabled = enabled) else alarm
            }
        }
    }

//    fun updateActiveDay(index: Int, day: String, active: Boolean) {
//        _alarms.update { list ->
//            list.mapIndexed { i, alarm ->
//                if (i == index) {
//                    val updatedDays = if (active) {
//                        alarm.activeDays + day
//                    } else {
//                        alarm.activeDays - day
//                    }
//                    alarm.copy(activeDays = updatedDays)
//                } else alarm
//            }
//        }
//    }

    fun addAlarm(alarm: AlarmUiModel) {
        _alarms.update { it + alarm }
    }

    fun removeAlarm(index: Int) {
        _alarms.update { it.toMutableList().apply { removeAt(index) } }
    }
}