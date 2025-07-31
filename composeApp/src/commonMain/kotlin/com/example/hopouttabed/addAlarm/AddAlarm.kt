package com.example.hopouttabed.addAlarm


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hopouttabed.addAlarm.basicAlarmSettings.BasicAlarmSettings
import com.example.hopouttabed.addAlarm.basicAlarmSettings.SoundSetting
import com.example.hopouttabed.addAlarm.viewModel.AlarmSoundUiState
import com.example.hopouttabed.addAlarm.viewModel.BasicAlarmSettingsCallbacks
import com.example.hopouttabed.addAlarm.viewModel.BasicAlarmSettingsViewModel
import com.example.hopouttabed.addAlarm.viewModel.TimePickerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAlarmScreen(
    onSave: () -> Unit,
    onBackConfirmed: () -> Unit,
    onNavigateToSoundPicker: () -> Unit,
    alarmSoundUiState: AlarmSoundUiState
) {
    val timePickerVM: TimePickerViewModel = viewModel()
    val timePickerUiState by timePickerVM.uiState.collectAsStateWithLifecycle()

    val alarmSettingsVm: BasicAlarmSettingsViewModel = viewModel()
    val alarmSettingsUiState by alarmSettingsVm.uiState.collectAsStateWithLifecycle()

    var showConfirmDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AddAlarmTopAppBar(onBack = { showConfirmDialog = true })
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 24.dp, horizontal = 16.dp)
        ) {
            WheelTimePicker(
                timePickerUiState = timePickerUiState,
                onTimeChanged = { timePickerVM::updateTime }
            )

            Spacer(modifier = Modifier.height(24.dp))

            BasicAlarmSettings(
                basicSettingsUiState = alarmSettingsUiState,
                callbacks = BasicAlarmSettingsCallbacks(
                    toggleVibrate = alarmSettingsVm::toggleVibrate,
                    toggleSnooze = alarmSettingsVm::toggleSnooze,
                    updateSound = alarmSettingsVm::updateSound,
                )
            )

            SoundSetting(
                label = "Sound",
                alarmSoundUiState = alarmSoundUiState,
                onNavigateToSoundPicker = onNavigateToSoundPicker
            )

            MissionSection()

            Spacer(modifier = Modifier.weight(1f))

            FloatingActionButton(
                onClick = onSave,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = MaterialTheme.shapes.medium,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 10.dp
                ),
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("Save", style = MaterialTheme.typography.bodyLarge)
            }
        }

        DiscardAlarmConfirmDialog(
            showConfirmDialog = showConfirmDialog,
            onDismiss = { showConfirmDialog = false },
            onBackConfirmed = onBackConfirmed
        )
    }
}
