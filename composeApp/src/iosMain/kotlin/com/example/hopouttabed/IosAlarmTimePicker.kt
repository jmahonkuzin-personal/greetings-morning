package com.example.hopouttabed

import androidx.compose.runtime.Composable

// iosMain
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class AlarmTimePickerProvider {
    actual fun getTimePicker(): AlarmTimePicker = IOSAlarmTimePicker()
}

class IOSAlarmTimePicker : AlarmTimePicker {
    @Composable
    override fun TimePicker(
        initialHour: Int,
        initialMinute: Int,
        is24Hour: Boolean,
        onConfirm: (hour: Int, minute: Int) -> Unit,
        onDismiss: () -> Unit
    ) {
        // Native iOS picker or placeholder
        IOSPicker()
    }

    @Composable
    fun IOSPicker() {
        // Compose Multiplatform doesn't have a native iOS time picker yet,
        // so you might create your own wheel-style UI or show a placeholder
        androidx.compose.material3.Text("iOS Time Picker (implement custom)")
    }
}