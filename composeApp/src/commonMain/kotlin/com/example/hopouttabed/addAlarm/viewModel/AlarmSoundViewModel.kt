package com.example.hopouttabed.addAlarm.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AlarmSoundUiState(
    val sound: String = ""
)

class AlarmSoundViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AlarmSoundUiState())
    val uiState: StateFlow<AlarmSoundUiState> = _uiState.asStateFlow()

    fun updateSound(sound: String) {
        _uiState.update { current ->
            current.copy(
                sound = sound
            )
        }
    }
}