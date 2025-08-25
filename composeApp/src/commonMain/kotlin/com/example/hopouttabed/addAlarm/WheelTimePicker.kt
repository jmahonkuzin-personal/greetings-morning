package com.example.hopouttabed.addAlarm

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hopouttabed.addAlarm.viewModel.AlarmUiState
import com.example.hopouttabed.addAlarm.viewModel.Meridiem
import kotlinx.datetime.LocalTime

@Composable
fun WheelTimePicker(
    alarmUiState: AlarmUiState,
    onTimeChanged: (LocalTime) -> Unit
) {
    val initialTime = alarmUiState.time
    val is24Hour = true // TODO read from settings
    var selectedHour by remember { mutableStateOf(initialTime.hour) }
    var selectedMinute by remember { mutableStateOf(initialTime.minute) }
    var meridiem by remember {
        mutableStateOf(
            if (selectedHour >= 12) Meridiem.PM else Meridiem.AM
        )
    }

    val hours = if (is24Hour) (0..23).toList() else (1..12).toList()
    val minutes = (0..59).toList()

    Box() {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            InfiniteWheelColumn(
                items = hours,
                selectedItem = if (is24Hour) selectedHour else (selectedHour % 12).let { if (it == 0) 12 else it },
                onItemSelected = { hour ->
                    selectedHour = if (is24Hour) hour else {
                        if (meridiem == Meridiem.PM && hour < 12) hour + 12
                        else if (meridiem == Meridiem.AM && hour == 12) 0
                        else hour
                    }
                    onTimeChanged(LocalTime(selectedHour, selectedMinute))
                }
            )
            InfiniteWheelColumn(
                items = minutes,
                selectedItem = selectedMinute,
                onItemSelected = {
                    selectedMinute = it
                    onTimeChanged(LocalTime(selectedHour, selectedMinute))
                }
            )
        }
        if (!is24Hour) {
            WheelColumn(
                items = listOf("AM", "PM"),
                selectedItem = if (meridiem == Meridiem.AM) "AM" else "PM",
                onItemSelected = { label ->
                    meridiem = if (label == "AM") Meridiem.AM else Meridiem.PM
                    selectedHour = if (meridiem == Meridiem.PM && selectedHour < 12) selectedHour + 12
                    else if (meridiem == Meridiem.AM && selectedHour >= 12) selectedHour - 12
                    else selectedHour
                    onTimeChanged(LocalTime(selectedHour, selectedMinute))
                },
                modifier = Modifier.align(Alignment.CenterEnd).padding(end = 40.dp)
            )
        }
        // 👇 These dividers frame the selected time
        HorizontalDivider(
            color = MaterialTheme.colorScheme.secondary,
            thickness = 1.dp,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = -40.dp)
                .fillMaxWidth(0.9f)
        )
        HorizontalDivider(
            color = MaterialTheme.colorScheme.secondary,
            thickness = 1.dp,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 24.dp)
                .fillMaxWidth(0.9f)
        )
    }
}
