package com.example.hopouttabed.dashboard.alarmCard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hopouttabed.dashboard.viewModel.AlarmDayOfWeek
import com.example.hopouttabed.data.Alarm

@Composable
fun AlarmCard(
    alarm: Alarm,
    onToggle: (Boolean) -> Unit
) {

    val backgroundColor = if (alarm.enabled) {
        MaterialTheme.colorScheme.secondary // Gunmetal
    } else {
        MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f)
    }

    val timeColor = if (alarm.enabled)
        MaterialTheme.colorScheme.onSecondary
    else
        MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f)

    Card(
        modifier = Modifier.fillMaxWidth().height(100.dp), // <-- Consistent fixed height,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 2.dp), // 👈 inner padding for all child content
            contentAlignment = Alignment.Center
        ) {
            MissionDisplay(
                alarm = alarm,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            Column(
                modifier = Modifier.padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "${alarm.hour} : ${alarm.minute} AM", // TODO fix
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.5.sp
                    ),
                    color = timeColor
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Active Days Row
                val allDays = AlarmDayOfWeek.entries
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
                ) {
                    allDays.forEach { day ->
                        val isActive = day in alarm.activeDays
                        Text(
                            text = day.displayName,
                            color = if (isActive) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onSecondary.copy(
                                alpha = 0.4f
                            ),
                            fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    }
                }
            }
            Switch(
                checked = alarm.enabled,
                onCheckedChange = onToggle,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = MaterialTheme.colorScheme.primary,
                    uncheckedThumbColor = Color.LightGray,
                    uncheckedTrackColor = Color.Gray
                ),
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}
