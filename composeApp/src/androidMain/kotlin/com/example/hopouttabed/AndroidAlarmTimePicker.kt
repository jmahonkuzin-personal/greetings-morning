package com.example.hopouttabed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hopouttabed.time.M3TimePickerDialog
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format
import java.time.format.DateTimeFormatter

// androidMain
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class AlarmTimePickerProvider {
    actual fun getTimePicker(): AlarmTimePicker = AndroidAlarmTimePicker()
}

class AndroidAlarmTimePicker : AlarmTimePicker {
    @Composable
    override fun TimePicker(
        onConfirm: (hour: Int, minute: Int) -> Unit,
        onDismiss: () -> Unit
    ) {
        M3TimePickerDialog(
            onDismiss = onDismiss,
            onConfirm = onConfirm
        )
        // You can use a Material Time Picker or a custom one
//        AndroidTimePicker()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AndroidTimePicker(
        onConfirm: () -> Unit,
                          onDismiss: () -> Unit
    ) {
        val timePickerState = rememberTimePickerState(
            initialHour = 7,
            initialMinute = 30,
            is24Hour = false // gives you meridiem
        )
        val initialTime = LocalTime(7, 30)

        var showTimePicker by remember { mutableStateOf(false) }

//        Column(modifier = Modifier.padding(16.dp)) {
//            Text("Alarm time: ${initialTime.format(DateTimeFormatter.ofPattern("hh:mm a"))}")
//
//            Button(onClick = { showTimePicker = true }) {
//                Text("Select Time")
//            }
//
//            if (showTimePicker) {
//                TimePickerSection(
//                    timePickerState = timePickerState,
//                    onConfirm = onConfirm,
//                    onDismiss = onDismiss
//                )
//            }
//
//        }
    }
}