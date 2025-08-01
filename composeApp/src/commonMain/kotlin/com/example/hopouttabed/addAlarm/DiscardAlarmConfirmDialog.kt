package com.example.hopouttabed.addAlarm


import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscardAlarmConfirmDialog(
    showConfirmDialog: Boolean,
    onDismiss: () -> Unit,
    onBackConfirmed: () -> Unit
) {
    if (showConfirmDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            textContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(0.8f),
            title = { Text("Discard Alarm?") },
            text = { Text("If you go back now, your changes will be lost.") },
            shape = MaterialTheme.shapes.large,
            confirmButton = {
                TextButton(
                    onClick = onBackConfirmed,
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Discard")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismiss,
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(0.8f),)
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}
