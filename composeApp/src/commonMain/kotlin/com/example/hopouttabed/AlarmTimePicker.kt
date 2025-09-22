package com.example.hopouttabed

import androidx.compose.runtime.Composable

// commonMain
interface AlarmTimePicker {
    @Composable
    fun TimePicker(
        initialHour: Int = 0,
        initialMinute: Int = 0,
        is24Hour: Boolean = false,
        onConfirm: (hour: Int, minute: Int) -> Unit,
        onDismiss: () -> Unit
    )
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class AlarmTimePickerProvider() { //the parentheses () after the class name declares a default constructor.
    fun getTimePicker(): AlarmTimePicker
}

/** Tiny host you can call anywhere in common code */
@Composable
fun AlarmTimePickerHost(
    show: Boolean,
    onClose: () -> Unit,
    onConfirm: (hour: Int, minute: Int) -> Unit,
) {
    if (!show) return
    val provider = AlarmTimePickerProvider()
    val picker = provider.getTimePicker()
    picker.TimePicker(
        onConfirm = { h, m ->
            onConfirm(h, m)
            onClose()
        },
        onDismiss = onClose
    )
}