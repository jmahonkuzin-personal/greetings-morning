package com.example.hopouttabed.dashboard.alarmCard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hopouttabed.dashboard.viewModel.AlarmUiModel

@Composable
fun AlarmCard(
    alarm: AlarmUiModel,
    onToggleEnabled: (Boolean) -> Unit
) {
    val backgroundColor = if (alarm.enabled) {
        MaterialTheme.colorScheme.surface
    } else {
        MaterialTheme.colorScheme.surface.copy(alpha = 0.4f) // dimmed background for disabled
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = MaterialTheme.shapes.medium)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        MissionDisplay(
            alarm = alarm,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${alarm.time} ${alarm.amPm}",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Active Days Row
            val allDays = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
            ) {
                allDays.forEach { day ->
                    val isActive = day in alarm.activeDays
                    Text(
                        text = day,
                        color = if (isActive) Color.Black else Color.Gray,
                        fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            }
        }
        Switch(
            checked = alarm.enabled,
            onCheckedChange = { onToggleEnabled(it) },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFFFFB6B6),
                uncheckedThumbColor = Color.LightGray,
                uncheckedTrackColor = Color.Gray
            ),
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}
