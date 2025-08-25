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
import com.example.hopouttabed.addAlarm.basicAlarmSettings.MissionSettingsSection
import com.example.hopouttabed.addAlarm.basicAlarmSettings.SelectedSoundSetting
import com.example.hopouttabed.addAlarm.viewModel.AlarmCallbacks
import com.example.hopouttabed.addAlarm.viewModel.AlarmSoundUiState
import com.example.hopouttabed.addAlarm.viewModel.AlarmViewModel
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

    var showConfirmDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AddAlarmTopAppBar(onBack = { showConfirmDialog = true })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 24.dp, horizontal = 16.dp)
        ) {
            WheelTimePicker(
                alarmUiState = alarmUiState,
                onTimeChanged = { alarmVM::updateTime }
            )

            Spacer(modifier = Modifier.height(24.dp))

            BasicAlarmSettings(
                alarmUiState = alarmUiState,
                callbacks = AlarmCallbacks(
                    toggleVibrate = alarmVM::toggleVibrate,
                    toggleSnooze = alarmVM::toggleSnooze
                )
            )

            SelectedSoundSetting(
                selectedSound = alarmSoundUiState.sound,
                onNavigateToSoundPicker = onNavigateToSoundPicker
            )

            MissionSettingsSection()
            Text(
                text = "Block phone for X time after waking up",
                color = Color.Cyan
            )
            Text(
                text = "Choose a song to play in the morning",
                color = Color.Cyan
            )
            Text(
                text = "Speak kindly to yourself -- play an audio where you speak kindly to yourself",
                color = Color.Cyan
            )
            Text(
                text = "Connect to your vision -- through journaling or audio or images",
                color = Color.Cyan
            )
            Text(
                text = "Breathwork",
                color = Color.Cyan
            )

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
