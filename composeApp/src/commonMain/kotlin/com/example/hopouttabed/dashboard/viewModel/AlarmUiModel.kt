package com.example.hopouttabed.dashboard.viewModel

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
    val enabled: Boolean,
    val activeDays: Set<AlarmDayOfWeek> = setOf(AlarmDayOfWeek.Monday, AlarmDayOfWeek.Tuesday, AlarmDayOfWeek.Wednesday)
)


enum class AlarmDayOfWeek(val displayName: String) {
    Sunday("S"),
    Monday("M"),
    Tuesday("T"),
    Wednesday("W"),
    Thursday("T"),
    Friday("F"),
    Saturday("S");

    override fun toString(): String = displayName

    companion object {
        fun fromDisplayName(name: String): AlarmDayOfWeek? =
            entries.firstOrNull { it.displayName == name }
    }
}

@Composable
fun missionToIcon(mission: String): ImageVector {
    return when (mission) {
        "light" -> Icons.Default.WbSunny
        "megaphone" -> Icons.Default.Campaign
        "sound" -> Icons.Default.GraphicEq
        else -> Icons.Default.Check
    }
}
