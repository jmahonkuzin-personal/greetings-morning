package com.example.hopouttabed

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.time.LocalTime


import hopouttabed.composeapp.generated.resources.Res
import hopouttabed.composeapp.generated.resources.compose_multiplatform

@Composable
fun AddAlarmScreen() {
    val time = remember { mutableStateOf(LocalTime.of(6, 30)) }
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

@Composable
fun TimePickerSection(time: MutableState<LocalTime>, isAm: MutableState<Boolean>) {
    // Use your own implementation or 3rd-party picker for a real time picker.
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            "${time.value.hour} : ${"%02d".format(time.value.minute)}",
            style = MaterialTheme.typography.displayMedium,
            color = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        DropdownMenuSelector(
            options = listOf("AM", "PM"),
            selected = if (isAm.value) "AM" else "PM",
            onSelect = { isAm.value = it == "AM" }
        )
    }
}

@Composable
fun DropdownMenuSelector(options: List<String>, selected: String, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(
            onClick = { expanded = true },
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
        ) {
            Text(selected, color = Color.White)
            Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.White)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        onSelect(it)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun SettingsRow(
    label: String,
    switchState: MutableState<Boolean>? = null,
    trailingContent: @Composable RowScope.() -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.DarkGray, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, color = Color.White)

        if (switchState != null) {
            Switch(
                checked = switchState.value,
                onCheckedChange = { switchState.value = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFFFFB6B6),
                    uncheckedThumbColor = Color.LightGray
                )
            )
        } else {
            trailingContent()
        }
    }
}

@Composable
fun MissionSection(
    missionStates: SnapshotStateList<Boolean>,
    icons: List<ImageVector>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.DarkGray, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text("Mission", color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            icons.forEachIndexed { index, icon ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Checkbox(
                        checked = missionStates.getOrNull(index) == true,
                        onCheckedChange = { missionStates[index] = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFFFFB6B6),
                            checkmarkColor = Color.Black
                        )
                    )
                    Icon(icon, contentDescription = null, tint = Color.White)
                }
            }
        }
    }
}
