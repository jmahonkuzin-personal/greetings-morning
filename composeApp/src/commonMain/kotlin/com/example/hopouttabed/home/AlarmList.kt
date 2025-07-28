package com.example.hopouttabed.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AlarmList(alarms: List<AlarmUiModel>) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Icon(Icons.Default.Alarm, contentDescription = null, tint = Color.White)
            Spacer(Modifier.width(8.dp))
            Text("Alarms", color = Color.White, style = MaterialTheme.typography.titleMedium)
        }

        alarms.forEach { alarm ->
            AlarmCard(alarm)
        }
    }
}
