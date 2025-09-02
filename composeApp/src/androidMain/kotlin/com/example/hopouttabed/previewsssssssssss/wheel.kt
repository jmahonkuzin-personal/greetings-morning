package com.example.hopouttabed.previewsssssssssss

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hopouttabed.dashboard.alarmCard.AlarmCard
import com.example.hopouttabed.dashboard.viewModel.AlarmUiModel
import com.example.hopouttabed.theme.WakeUpAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun AlarmList(alarms: List<AlarmUiModel>, onToggle: (index: Int, isEnabled: Boolean) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Alarm,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Alarms",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium
            )
        }

        alarms.forEachIndexed { index, alarm ->
            AlarmCard(alarm, onToggleEnabled = { isEnabled -> onToggle(index, isEnabled) })
        }
    }
}

@Preview
@Composable
fun AlarmListPreview() {

    WakeUpAppTheme {
        val alarms = remember {
            listOf(
                AlarmUiModel("7:30", "AM", listOf("light", "megaphone", "sound"), enabled = true),
                AlarmUiModel("9:00", "AM", listOf("sound"), enabled = true),
                AlarmUiModel("6:00", "AM", listOf("light", "sound"), enabled = false),
                AlarmUiModel("6:30", "AM", listOf("sound"), enabled = false),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black), // Preview background
            contentAlignment = Alignment.Center
        ) {
            @Suppress("UNCHECKED_CAST")
            AlarmList(
                alarms,
                onToggle = { } as (Int, Boolean) -> Unit
            )
        }
    }
}

@Preview
@Composable
fun DiscardAlarmPreview() {
    var showConfirmDialog = true
    WakeUpAppTheme {
        AlertDialog(
            onDismissRequest = { showConfirmDialog = false },
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            textContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(0.8f),
            title = { Text("Discard Alarm?") },
            text = { Text("If you go back now, your changes will be lost.") },
            shape = MaterialTheme.shapes.large,
            confirmButton = {
                TextButton(
                    onClick = {  },
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Discard")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showConfirmDialog = false },
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(0.8f),)
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}