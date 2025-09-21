package com.example.hopouttabed.time

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Keyboard
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
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
                TimeInput(
                    state = state
                )
            } else {
                TimePicker(
                    state = state,
                    colors = TimePickerDefaults.colors(
                        clockDialColor = MaterialTheme.colorScheme.surfaceVariant,          // #262A2E
                        clockDialSelectedContentColor = MaterialTheme.colorScheme.onPrimary,// text on selected circle
                        clockDialUnselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
//                        clockDialSeletedColor = MaterialTheme.colorScheme.primary,         // #D9A5B3 (pink puck)
                        periodSelectorSelectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        periodSelectorSelectedContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        periodSelectorUnselectedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        periodSelectorUnselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
//                    colors = TimePickerDefaults.colors(
//
//                    )
                )
            }
        }
    }
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
