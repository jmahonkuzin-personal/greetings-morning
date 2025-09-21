package com.example.hopouttabed.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hopouttabed.data.Alarm
import com.example.hopouttabed.data.AlarmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class AlarmDashboardUiState(
    val alarms: List<Alarm> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)

class AlarmDashboardViewModel(
    private val repository: AlarmRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AlarmDashboardUiState())
    val uiState: StateFlow<AlarmDashboardUiState> = _uiState.asStateFlow()

    fun loadAlarms() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val storedAlarms = repository.getAll()

                _uiState.update { it.copy(alarms = storedAlarms, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun delete(alarm: Alarm) {
        viewModelScope.launch { repository.delete(alarm) }
    }

//    fun setEnabled(alarmUuid: String, enabled: Boolean) {
//        viewModelScope.launch { repository.setEnabled(alarmUuid, enabled) }
//    }
}