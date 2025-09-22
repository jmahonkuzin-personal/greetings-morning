package com.example.hopouttabed

import androidx.compose.runtime.Composable
import com.example.hopouttabed.data.DI
import com.example.hopouttabed.theme.WakeUpAppTheme

@Composable
fun App() {
    DI.init()
    WakeUpAppTheme {
        AlarmNavHost()
    }
}