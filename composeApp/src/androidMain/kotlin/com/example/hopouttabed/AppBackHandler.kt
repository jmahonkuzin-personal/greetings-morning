package com.example.hopouttabed

import androidx.compose.runtime.Composable
import androidx.activity.compose.BackHandler as AndroidBackHandler

@Composable
actual fun AppBackHandler(enabled: Boolean, onBack: () -> Unit) {
    AndroidBackHandler(enabled, onBack)
}