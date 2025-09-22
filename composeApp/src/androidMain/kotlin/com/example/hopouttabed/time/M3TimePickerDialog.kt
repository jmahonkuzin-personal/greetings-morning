package com.example.hopouttabed.time

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Keyboard
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hopouttabed.theme.WakeUpAppTheme


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
        containerColor = MaterialTheme.colorScheme.secondary,
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onConfirm(state.hour, state.minute)
            }) {
                Text(text = "OK", color = MaterialTheme.colorScheme.primary)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(text = "Cancel", color = MaterialTheme.colorScheme.onSurfaceVariant) }
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
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Select time",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        content = {
            if (inputMode) {
                TimeInput(
                    state = state,
                    colors = timePickerDefaultColors()
                )
            } else {
                TimePicker(
                    state = state,
                    colors = timePickerDefaultColors()
                )
            }
        }
    )
}

@Preview
@Composable
fun M3TimePickerDialogPrev() {
    WakeUpAppTheme {
        M3TimePickerDialog(
            onDismiss = {},
            onConfirm = { hour: Int, minute: Int -> /* no-op for preview */ }
            // or: onConfirm = { _, _ -> }
        )
    }
}
