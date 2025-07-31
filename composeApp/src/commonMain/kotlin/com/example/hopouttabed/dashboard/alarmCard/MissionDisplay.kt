package com.example.hopouttabed.dashboard.alarmCard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hopouttabed.dashboard.viewModel.AlarmUiModel
import com.example.hopouttabed.dashboard.viewModel.missionToIcon

@Composable
fun MissionDisplay(alarm: AlarmUiModel, modifier: Modifier) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        alarm.missionIcons.forEach {
            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .border(1.dp, Color.Black, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = missionToIcon(it),
                    contentDescription = it,
                    tint = when (it) {
                        "light" -> Color.Yellow
                        "megaphone" -> Color.Cyan
                        else -> Color.Green
                    },
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}