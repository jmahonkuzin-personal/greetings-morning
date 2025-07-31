package com.example.hopouttabed.addAlarm.basicAlarmSettings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hopouttabed.addAlarm.viewModel.AlarmSoundUiState

@Composable
fun AlarmSoundPickerScreen(
    onBack: () -> Unit,
    alarmSoundUiState: AlarmSoundUiState,
    onSoundSelection: (String) -> Unit
) {
    val selectedSound = alarmSoundUiState.sound
    var shouldPlaySoundOnSelection by remember { mutableStateOf(false) }

    val sounds = listOf(
        "Beep Beep", "Beacon", "Radar", "Bell Ring", "Sunrise Chimes", "Uplift",
        "Dream Pop", "Happy Vibes", "Birdsong", "Ocean Waves", "Rainfall",
        "Siren Sprint", "Soft Gong", "Cosmic Hum", "Game Start"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        // Top bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .clickable { onBack() }  // Make back arrow clickable
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
            Spacer(Modifier.width(8.dp))
            Text(
                "Sound",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        // Selected Sound Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Selected", color = Color.LightGray)
            Text(selectedSound, color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(12.dp))

        // Play toggle
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Play alarm sound",
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
            Checkbox(
                checked = shouldPlaySoundOnSelection,
                onCheckedChange = { shouldPlaySoundOnSelection = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFFF6AEB2),
                    uncheckedColor = Color.White
                )
            )
        }

        Spacer(Modifier.height(12.dp))

        // Sound list
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(sounds) { sound ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray, shape = RoundedCornerShape(4.dp))
                        .padding(12.dp)
                        .clickable {
                            onSoundSelection(sound)
                            if (shouldPlaySoundOnSelection) {
                                // TODO: Play preview here
                            }
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        sound,
                        modifier = Modifier.weight(1f),
                        color = Color.White
                    )
                    RadioButton(
                        selected = sound == selectedSound,
                        onClick = { onSoundSelection(sound) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.White,
                            unselectedColor = Color.LightGray
                        )
                    )
                }
            }
        }
    }
}
