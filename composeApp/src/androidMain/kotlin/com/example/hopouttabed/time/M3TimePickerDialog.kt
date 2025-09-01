package com.example.hopouttabed.time

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Keyboard
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import java.time.LocalTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun M3TimePickerDialog(
    initialHour: Int = 7,
    initialMinute: Int = 30,
    is24Hour: Boolean = false,
    onDismiss: () -> Unit,
    onConfirm: (hour: Int, minute: Int) -> Unit
) {
    val state = rememberTimePickerState(
        initialHour = initialHour,
        initialMinute = initialMinute,
        is24Hour = is24Hour
    )
    var inputMode by remember { mutableStateOf(false) }

    TimePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = { onConfirm(state.hour, state.minute) }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        },
        title = {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Select time", style = MaterialTheme.typography.titleLarge)
                IconButton(onClick = { inputMode = !inputMode }) {
                    Icon(
                        imageVector = if (inputMode) Icons.Outlined.Schedule else Icons.Outlined.Keyboard,
                        contentDescription = if (inputMode) "Switch to dial" else "Switch to input"
                    )
                }
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
