package com.example.hopouttabed

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import com.example.hopouttabed.time.M3TimePickerDialog

// androidMain
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class AlarmTimePickerProvider {
    actual fun getTimePicker(): AlarmTimePicker = AndroidAlarmTimePicker()
}

class AndroidAlarmTimePicker : AlarmTimePicker {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun TimePicker(
        initialHour: Int,
        initialMinute: Int,
        is24Hour: Boolean,
        onConfirm: (hour: Int, minute: Int) -> Unit,
        onDismiss: () -> Unit
    ) {
        val state = rememberTimePickerState(
            initialHour = initialHour,
            initialMinute = initialMinute,
            is24Hour = is24Hour
        )
        M3TimePickerDialog(
            state = state,
            onDismiss = onDismiss,
            onConfirm = onConfirm
        )
    }
}