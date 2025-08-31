package com.example.hopouttabed

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.hopouttabed.addAlarm.time.WheelTimePickerWrapper
import com.example.hopouttabed.theme.WakeUpAppTheme
import kotlinx.datetime.LocalTime
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    WakeUpAppTheme {
        // Start with a sample time (7:30)
        var previewTime by remember { mutableStateOf(LocalTime(7, 30)) }

        WakeUpAppTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                WheelTimePickerWrapper(
                    time = previewTime,
                    onTimeChanged = { previewTime = it } // updates local state so preview stays reactive
                )
            }
        }
//        AlarmDemoScreen()
//        AlarmNavHost()
    }
}