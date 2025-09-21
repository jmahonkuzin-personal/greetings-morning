package com.example.hopouttabed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

/**
 * For the alarm overlay!
 */
class AlarmActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlarmScreen(
                onDismiss = { finish() },
                onSnooze = { finish() }
            )
        }
    }
}