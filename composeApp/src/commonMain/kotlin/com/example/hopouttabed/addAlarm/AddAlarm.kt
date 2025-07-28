package com.example.hopouttabed.addAlarm


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalTime

@Composable
fun AddAlarmScreen() {

    val time = remember { mutableStateOf(LocalTime(hour = 7, minute = 59, second = 12)) }
    val isAm = remember { mutableStateOf(true) }

    val vibrateEnabled = remember { mutableStateOf(true) }
    val snoozeEnabled = remember { mutableStateOf(true) }
    val selectedSound = remember { mutableStateOf("Beacon") }

    val missionStates = remember {
        mutableStateListOf(true, true, false, true)
    }

    val missionIcons = listOf(
        Icons.Default.Check,  // Placeholder for pink checkbox
        Icons.Default.WbSunny, // Light
        Icons.Default.Campaign, // Megaphone
        Icons.Default.GraphicEq // Sound wave
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            "Add Alarm",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.White
        )

        Spacer(modifier = Modifier.height(24.dp))

        TimePickerSection(time = time, isAm = isAm)

        Spacer(modifier = Modifier.height(24.dp))

        SettingsRow("Vibrate", switchState = vibrateEnabled)
        SettingsRow("Sound", trailingContent = {
            Text(selectedSound.value, color = Color.White)
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = Color.White)
        })
        SettingsRow("Snooze", switchState = snoozeEnabled)

        MissionSection(missionStates, missionIcons)

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { /* Save alarm */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB6B6)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Save", color = Color.White)
        }
    }
}
