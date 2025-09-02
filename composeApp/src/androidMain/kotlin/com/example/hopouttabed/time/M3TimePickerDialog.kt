package com.example.hopouttabed.time

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Keyboard
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun M3TimePickerDialog(
    initialHour: Int = 7,
    initialMinute: Int = 30,
    is24Hour: Boolean = false,
    onDismiss: () -> Unit,
    onConfirm: (hour: Int, minute: Int) -> Unit,
    state: TimePickerState
) {
    var showPicker by rememberSaveable { mutableStateOf(true) }

    val state = rememberTimePickerState(
        initialHour = initialHour,
        initialMinute = initialMinute,
        is24Hour = is24Hour
    )
    var inputMode by remember { mutableStateOf(false) }

    if (showPicker) {
        TimePickerDialog(
            onDismissRequest = {
                onDismiss
                showPicker = false
            },
            confirmButton = {
                TextButton(onClick = {
                    onConfirm(state.hour, state.minute)
                    showPicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    onDismiss
                    showPicker = false
                }) { Text("Cancel") }
            },
            modeToggleButton = {
                IconButton(onClick = { inputMode = !inputMode }) {
                    Icon(
                        imageVector = if (inputMode) Icons.Outlined.Schedule else Icons.Outlined.Keyboard,
                        contentDescription = if (inputMode) "Switch to dial" else "Switch to input"
                    )
                }
            },
            title = {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Select time", style = MaterialTheme.typography.titleLarge)
                }
            }
        ) {
            if (inputMode) {
                TimeInput(state = state)
            } else {
                TimePicker(state = state)
            }
        }
    }
}
