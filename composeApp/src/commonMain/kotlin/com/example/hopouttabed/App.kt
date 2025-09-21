package com.example.hopouttabed

import androidx.compose.runtime.Composable
import com.example.hopouttabed.data.DI
import com.example.hopouttabed.theme.WakeUpAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    DI.init()
    WakeUpAppTheme {
        AlarmNavHost()
    }
}