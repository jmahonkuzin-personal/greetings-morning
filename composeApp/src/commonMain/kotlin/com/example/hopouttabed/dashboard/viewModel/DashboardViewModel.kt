package com.example.hopouttabed.dashboard.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hopouttabed.data.AlarmRepository
import com.example.hopouttabed.data.DI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repository: AlarmRepository = DI.alarmRepository
) : ViewModel() {


    private val _alarms = MutableStateFlow<List<AlarmUiModel>>(emptyList())

    init {
        viewModelScope.launch {
            repository.getAll().collect { alarms ->
                _alarms.value = alarms.map { alarm ->
                    val time = "${alarm.hour}:${alarm.minute.toString().padStart(2, '0')}" // Format time
                    val amPm = if (alarm.hour < 12) "AM" else "PM"
                    val enabled = alarm.enabled
                    AlarmUiModel(
                        time = time,
                        amPm = amPm,
                        enabled = enabled
                    )
                }
            }
        }
    }


    val alarms: StateFlow<List<AlarmUiModel>> = _alarms.asStateFlow()

    fun toggleAlarm(index: Int, enabled: Boolean) {
        viewModelScope.launch {
            val alarm = _alarms.value[index]
            repository.updateAlarm(index, alarm.copy(enabled = enabled))
        }
    }

    fun addAlarm(alarm: AlarmUiModel) {
        viewModelScope.launch {
            repository.insert(alarm)
        }
    }

    fun removeAlarm(index: Int) {
        viewModelScope.launch {
            repository.delete(index)
        }
    }
}