package com.example.hopouttabed

import androidx.compose.runtime.Composable

// commonMain
interface AlarmTimePicker {
    @Composable
    fun TimePicker(
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
    onDismiss: () -> Unit,
    onTimeSelected: (hour: Int, minute: Int) -> Unit,
    provider: AlarmTimePickerProvider = AlarmTimePickerProvider()
) {
    if (!show) return
    // Grab the platform picker once
    val picker = provider.getTimePicker()
    picker.TimePicker(
        onConfirm = { h, m -> onTimeSelected(h, m) },
        onDismiss = onDismiss
    )
}