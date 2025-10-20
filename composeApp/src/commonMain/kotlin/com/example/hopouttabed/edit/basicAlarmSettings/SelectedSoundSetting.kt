package com.example.hopouttabed.edit.basicAlarmSettings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.hopouttabed.viewModel.AlarmSound

@Composable
fun SelectedSoundSetting(
    selectedSound: AlarmSound,
    onNavigateToSoundPicker: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
//            .background(
//                color = MaterialTheme.colorScheme.secondary,
//                shape = MaterialTheme.shapes.medium
//            )
            .clickable(onClick = onNavigateToSoundPicker)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    if (dragAmount < -50f) { // swiping left to the sound screen
                        onNavigateToSoundPicker()
                    }
                }
            }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Sound",
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.weight(1f))

        Text(
            selectedSound.displayName,
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.headlineMedium
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.size(26.dp)
        )
    }
}
