package com.example.hopouttabed

import androidx.compose.runtime.Composable

// iosMain
@Composable
actual fun AppBackHandler(enabled: Boolean, onBack: () -> Unit) {
    // No-op on iOS
}