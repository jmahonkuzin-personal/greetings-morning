package com.example.hopouttabed.addAlarm.basicAlarmSettings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hopouttabed.addAlarm.viewModel.AlarmSoundUiState

@Composable
fun SoundSetting(
    label: String,
    alarmSoundUiState: AlarmSoundUiState,
    onNavigateToSoundPicker: () -> Unit,
) {
    val selectedSound = alarmSoundUiState.sound
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(vertical = 8.dp)
            .background(Color.DarkGray, shape = RoundedCornerShape(12.dp))
            .clickable(onClick = onNavigateToSoundPicker) // ✅ click support
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    if (dragAmount < -50f) { // ✅ swipe left
                        onNavigateToSoundPicker()
                    }
                }
            }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, color = Color.White)
        Spacer(modifier = Modifier.weight(1f)) // 👈 Push content to the right

        Text(selectedSound, color = Color.White, fontSize = 22.sp)
        Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = Color.White)
    }
}
