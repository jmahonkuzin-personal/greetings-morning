package com.example.hopouttabed.dashboard.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hopouttabed.data.Alarm
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


    private val _alarms = MutableStateFlow<List<Alarm>>(emptyList())

    init {
        viewModelScope.launch {
            repository.getAll().collect { alarms ->
                _alarms.value = alarms
            }
        }
    }


    val alarms: StateFlow<List<Alarm>> = _alarms.asStateFlow()

    fun toggleAlarm(alarm: Alarm, isEnabled: Boolean) {
        viewModelScope.launch {
            repository.updateAlarmEnabledState(alarm.uuid, isEnabled)
        }
    }

    fun addAlarm(alarm: Alarm) {
        viewModelScope.launch {
            repository.insert(alarm)
        }
    }

    fun removeAlarm(alarm: Alarm) {
        viewModelScope.launch {
            repository.delete(alarm)
        }
    }
}