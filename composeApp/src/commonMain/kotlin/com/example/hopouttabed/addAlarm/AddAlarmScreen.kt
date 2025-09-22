package com.example.hopouttabed.addAlarm


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hopouttabed.AlarmTimePickerHost
import com.example.hopouttabed.addAlarm.basicAlarmSettings.AlarmReminderText
import com.example.hopouttabed.addAlarm.basicAlarmSettings.BasicAlarmSettings
import com.example.hopouttabed.addAlarm.basicAlarmSettings.BlockAfterAlarm
import com.example.hopouttabed.addAlarm.basicAlarmSettings.MissionSettingsSection
import com.example.hopouttabed.addAlarm.basicAlarmSettings.SelectedSoundSetting
import com.example.hopouttabed.addAlarm.time.TimeSummaryStatic
import com.example.hopouttabed.addAlarm.time.timeUntilFun
import com.example.hopouttabed.theme.WakeUpAppTheme
import com.example.hopouttabed.viewModel.AlarmCallbacks
import com.example.hopouttabed.viewModel.AlarmSound
import com.example.hopouttabed.viewModel.AlarmUiState
import kotlinx.datetime.LocalTime
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAlarmScreen(
    onSave: () -> Unit,
    onBackConfirmed: () -> Unit,
    onNavigateToSoundPicker: () -> Unit,
    alarmUiState: AlarmUiState,
    alarmCallbacks: AlarmCallbacks
) {

    var showConfirmDialog by remember { mutableStateOf(false) }
    var isEditTime by remember { mutableStateOf(false) }
    val until = timeUntilFun(alarmUiState.time)

    Scaffold(
//        topBar = {
//            AddAlarmTopAppBar(onBack = { showConfirmDialog = true })
//        },
        bottomBar = {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FloatingActionButton(
                    onClick = { showConfirmDialog = true },
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    shape = MaterialTheme.shapes.medium,
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 10.dp
                    ),
                    modifier = Modifier
                        .height(48.dp)
                        .weight(1f)
                ) {
                    Text("Cancel", style = MaterialTheme.typography.bodyLarge)
                }

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
                        .weight(1f)
                ) {
                    Text("Save", style = MaterialTheme.typography.bodyLarge)
                }
            }

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 24.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Alarm in ${until.inWholeHours}h ${until.inWholeMinutes % 60}m")
            Spacer(modifier = Modifier.height(6.dp))
            TimeSummaryStatic(
                time = alarmUiState.time,
                onEdit = { isEditTime = true }
            )
//            Text(text = alarmUiState.time.toString())
            AlarmTimePickerHost(
                show = isEditTime,
                onClose = { isEditTime = false },
                onConfirm = alarmCallbacks.updateTime
            )
//            picker.TimePicker(
//                initialHour = 7,
//                initialMinute = 30,
//                is24Hour = false,
//                onConfirm = alarmCallbacks.updateTime,
//                onDismiss = { }
//            )
//            AlarmTimePickerHost(
//                show = true,
//                onDismiss = {},
//                onTimeSelected = alarmCallbacks.updateTime
//            )
//            WheelTimePickerWrapper(
//                time = alarmUiState.time,
//                onTimeChanged = alarmCallbacks.updateTime
//            )

            Spacer(modifier = Modifier.height(24.dp))

            AlarmReminderText()
            BlockAfterAlarm()

            BasicAlarmSettings(
                alarmUiState = alarmUiState,
                callbacks = alarmCallbacks
            )

            SelectedSoundSetting(
                selectedSound = alarmUiState.sound,
                onNavigateToSoundPicker = onNavigateToSoundPicker
            )

//            MissionSettingsSection()
//            Text(
//                text = "Block phone for X time after waking up",
//                color = Color.Cyan
//            )
//            Text(
//                text = "Choose a song to play in the morning",
//                color = Color.Cyan
//            )
//            Text(
//                text = "Speak kindly to yourself -- play an audio where you speak kindly to yourself",
//                color = Color.Cyan
//            )
//            Text(
//                text = "Connect to your vision -- through journaling or audio or images",
//                color = Color.Cyan
//            )
//            Text(
//                text = "Breathwork",
//                color = Color.Cyan
//            )
//
//            Text(
//                text = "Add a morning reminder",
//                color = Color.Cyan
//            )

        }

        DiscardAlarmConfirmDialog(
            showConfirmDialog = showConfirmDialog,
            onDismiss = { showConfirmDialog = false },
            onBackConfirmed = onBackConfirmed
        )
    }
}

@Preview
@Composable
fun AddAlarmScreenPreview() {
    // Mock state
    val previewUiState = AlarmUiState(
        time = LocalTime(7, 30),
        disabledMinutes = 15,
        allowedAppsDuringDisable = listOf("Messages", "Spotify"),
        hasVibrate = true,
        hasSnooze = false,
        sound = AlarmSound.Beacon,
        requireJournal = true
    )

    // Preview AlarmCallbacks (all no-ops)
    val previewCallbacks = AlarmCallbacks(
        updateTime = { _, _ -> },
        updateDisabledMinutes = { _ -> },
        updateAllowedAppsDuringDisable = { _ -> },
        toggleVibrate = { _ -> },
        toggleSnooze = { _ -> },
        updateSound = { _ -> },
        toggleRequireJournal = { _ -> }
    )

    WakeUpAppTheme {
        AddAlarmScreen(
            onSave = {},
            onBackConfirmed = {},
            onNavigateToSoundPicker = {},
            alarmUiState = previewUiState,
            alarmCallbacks = previewCallbacks
        )
    }
}

enum class Meridiem {
    AM, PM
}