package com.example.hopouttabed.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AlarmDashboardScreen() {
    val alarms = remember {
        listOf(
            AlarmUiModel("7:30", "AM", listOf("light", "megaphone", "sound"), true),
            AlarmUiModel("9:00", "AM", listOf("sound"), true),
            AlarmUiModel("6:00", "AM", listOf("light", "sound"), false),
            AlarmUiModel("6:30", "AM", listOf("sound"), false),
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(Modifier.height(16.dp))
            TopSection()
            Spacer(Modifier.height(24.dp))
            AlarmList(alarms)
        }

        FloatingActionButton(
            onClick = { /* add alarm */ },
            containerColor = Color.DarkGray,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
        }
    }
}
