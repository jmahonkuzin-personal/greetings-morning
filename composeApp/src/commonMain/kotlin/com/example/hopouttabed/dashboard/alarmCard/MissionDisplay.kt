package com.example.hopouttabed.dashboard.alarmCard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hopouttabed.data.Alarm

@Composable
fun MissionDisplay(alarm: Alarm, modifier: Modifier) {

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Icon(
            imageVector = Icons.Default.WbSunny,
            contentDescription = "Sunny Icon",
            tint = Color.Yellow,
            modifier = Modifier.size(16.dp)
        )
//        alarm.missionIcons.forEach {
//            Box(
//                modifier = Modifier
//                    .padding(end = 8.dp)
//                    .size(28.dp)
//                    .clip(CircleShape)
//                    .background(Color.Black.copy(alpha = 0.5f))
//                    .border(1.dp, Color.Black, CircleShape),
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    imageVector = Icons.Default.WbSunny, missionToIcon(it),
//                    contentDescription = it,
//                    tint = when (it) {
//                        "light" -> Color.Yellow
//                        "megaphone" -> Color.Cyan
//                        else -> Color.Green
//                    },
//                    modifier = Modifier.size(16.dp)
//                )
//            }
//        }
    }
}