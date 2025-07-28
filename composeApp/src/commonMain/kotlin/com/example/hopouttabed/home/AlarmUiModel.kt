package com.example.hopouttabed.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class AlarmUiModel(
    val time: String,
    val amPm: String,
    val missionIcons: List<String>,
    val enabled: Boolean
)

@Composable
fun missionToIcon(mission: String): ImageVector {
    return when (mission) {
        "light" -> Icons.Default.WbSunny
        "megaphone" -> Icons.Default.Campaign
        "sound" -> Icons.Default.GraphicEq
        else -> Icons.Default.Check
    }
}
