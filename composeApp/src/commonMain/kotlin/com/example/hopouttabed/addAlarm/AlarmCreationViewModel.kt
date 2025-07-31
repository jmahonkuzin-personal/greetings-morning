package com.example.hopouttabed.addAlarm

import androidx.lifecycle.ViewModel
import com.example.hopouttabed.addAlarm.viewModel.TimePickerViewModel

class AlarmCreationViewModel(
    private val alarmTimeViewModel: TimePickerViewModel,
//    private val missionsViewModel: MissionsViewModel,
//    private val soundViewModel: AlarmSoundViewModel,
//    private val alarmRepository: AlarmRepository
) : ViewModel() {

    fun saveAlarm() {
        val time = alarmTimeViewModel.uiState.value.time
//        val meridiem = alarmTimeViewModel.meridiem.value
//        val missions = missionsViewModel.selectedMissions.value
//        val sound = soundViewModel.selectedSound.value

//        val alarm = AlarmEntity(
//            time = time,
//            isAm = meridiem == Meridiem.AM,
//            missions = missions,
//            sound = sound
//        )
//
//        viewModelScope.launch {
//            alarmRepository.insertAlarm(alarm)
//        }
    }
}
