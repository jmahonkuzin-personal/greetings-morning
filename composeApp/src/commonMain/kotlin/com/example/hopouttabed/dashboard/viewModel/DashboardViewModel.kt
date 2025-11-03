package com.example.hopouttabed.dashboard.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hopouttabed.Logger
import com.example.hopouttabed.data.Alarm
import com.example.hopouttabed.data.AlarmRepository
import com.example.hopouttabed.data.DI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repository: AlarmRepository = DI.alarmRepository
) : ViewModel() {

    val alarms = repository.getAll()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )

    fun toggleAlarm(alarm: Alarm, isEnabled: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val numUpdated = repository.updateAlarmEnabledState(alarm.uuid, isEnabled)
            Logger.d("AlarmDao", "Updated $numUpdated rows for ${alarm.uuid}")
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