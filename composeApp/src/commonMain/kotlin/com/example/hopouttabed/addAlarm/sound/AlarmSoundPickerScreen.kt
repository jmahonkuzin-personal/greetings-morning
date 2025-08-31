package com.example.hopouttabed.addAlarm.sound

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hopouttabed.addAlarm.viewModel.AlarmSound
import com.example.hopouttabed.addAlarm.viewModel.AlarmSoundUiState
import com.example.hopouttabed.addAlarm.viewModel.AlarmUiState
import com.example.hopouttabed.theme.EvenLighterGunmetal
import com.example.hopouttabed.theme.WakeUpAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmSoundPickerScreen(
    onBack: () -> Unit,
    alarmUiState: AlarmUiState,
    onSoundSelection: (AlarmSound) -> Unit
) {
    val selectedSound = alarmUiState.sound

    val sounds = AlarmSound.entries

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
                .padding(start = 36.dp, end = 16.dp),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Choose Sound",
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
            },
            containerColor = MaterialTheme.colorScheme.background
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Selected sound
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(vertical = 8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Sound",
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        selectedSound.displayName,
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

                Spacer(Modifier.height(12.dp))

                PlaySoundToggle()

                Spacer(Modifier.height(8.dp))

                // Sound list
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(sounds) { sound ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .background(
                                    color = EvenLighterGunmetal,
                                    shape = MaterialTheme.shapes.medium
                                )
                                .clickable {
                                    onSoundSelection(sound)
//                                if (shouldPlaySoundOnSelection) {
//                                    // TODO: play sound preview
//                                }
                                }
                                .padding(horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = sound.displayName,
                                color = MaterialTheme.colorScheme.onSecondary,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.weight(1f)
                            )
                            RadioButton(
                                selected = sound == selectedSound,
                                onClick = { onSoundSelection(sound) },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colorScheme.background,
                                    unselectedColor = MaterialTheme.colorScheme.background
                                )
                            )
                        }
                    }
                }
            }
        }
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(32.dp),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview
@Composable
fun AlarmSoundPickerScreenPreview() {
    val fakeUiState = AlarmUiState(sound = AlarmSound.SunriseChimes)

    WakeUpAppTheme {
        AlarmSoundPickerScreen(
            onBack = {},
            alarmUiState = fakeUiState,
            onSoundSelection = {}
        )
    }
}