package com.example.hopouttabed

import androidx.compose.runtime.Composable

// commonMain
interface AlarmTimePicker {
    @Composable
    fun TimePicker(
        onConfirm: () -> Unit,
        onDismiss: () -> Unit
    )
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class AlarmTimePickerProvider() { //the parentheses () after the class name declares a default constructor.
    fun getTimePicker(): AlarmTimePicker
}