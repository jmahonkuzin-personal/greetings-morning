package com.example.hopouttabed.time

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun timePickerDefaultColors(): TimePickerColors =
    TimePickerDefaults.colors(
        // Dialog/card surface
        containerColor = MaterialTheme.colorScheme.secondary,

        // Big dial
        clockDialColor = MaterialTheme.colorScheme.surfaceVariant,
        clockDialUnselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        clockDialSelectedContentColor = MaterialTheme.colorScheme.onPrimary,
        selectorColor = MaterialTheme.colorScheme.primary,

        // AM/PM segmented control
        periodSelectorBorderColor = MaterialTheme.colorScheme.outline,
        periodSelectorSelectedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
        periodSelectorSelectedContentColor = MaterialTheme.colorScheme.onPrimary,
        periodSelectorUnselectedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
        periodSelectorUnselectedContentColor = MaterialTheme.colorScheme.onSurface,

        // “07 : 30” chips
        timeSelectorSelectedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
        timeSelectorSelectedContentColor = MaterialTheme.colorScheme.onPrimary,
        timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
        timeSelectorUnselectedContentColor = MaterialTheme.colorScheme.onSurface
    )
