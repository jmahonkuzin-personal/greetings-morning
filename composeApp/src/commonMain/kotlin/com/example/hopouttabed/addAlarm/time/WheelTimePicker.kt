package com.example.hopouttabed.addAlarm.time

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hopouttabed.addAlarm.viewModel.Meridiem
import com.example.hopouttabed.theme.WakeUpAppTheme
import kotlinx.datetime.LocalTime
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WheelTimePickerWrapper(
    time: LocalTime,
    onTimeChanged: (hour: Int, minute: Int) -> Unit
) {
    val bg = MaterialTheme.colorScheme.background
    val itemHeight = 64.dp
    val columnHeight = itemHeight * 3
    val modifier = Modifier.requiredHeight(columnHeight)

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

//        WheelTime(time, onTimeChanged, itemHeight, modifier)

        // Top scrim: background -> semi -> transparent
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight)
                .align(Alignment.TopCenter)
                .background(bg.copy(alpha = 0.6f))
        )
        // Bottom scrim: background -> semi -> transparent
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight)
                .align(Alignment.BottomCenter)
                .background(bg.copy(alpha = 0.6f))
        )
    }
}

@Composable
fun WheelTime(
    time: LocalTime,
    onTimeChanged: (LocalTime) -> Unit,
    itemHeight: Dp,
    modifier: Modifier
) {

    val is24Hour = false // TODO read from settings
    var selectedHour by remember { mutableStateOf(time.hour) }
    var selectedMinute by remember { mutableStateOf(time.minute) }
    var meridiem by remember {
        mutableStateOf(
            if (selectedHour >= 12) Meridiem.PM else Meridiem.AM
        )
    }
    val textFontSize = 50.sp

    val hours = if (is24Hour) (0..23).toList() else (1..12).toList()
    val minutes = (0..59).toList()

    Row(
        horizontalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Row {
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
                },
                itemHeight = itemHeight,
                modifier = modifier
            )
            // 👇 Colon separator
            Text(
                text = ":",
                fontSize = textFontSize,
//            style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .align(Alignment.CenterVertically)
            )
            InfiniteWheelColumn(
                items = minutes,
                selectedItem = selectedMinute,
                onItemSelected = {
                    selectedMinute = it
                    onTimeChanged(LocalTime(selectedHour, selectedMinute))
                },
                itemHeight = itemHeight,
                modifier = modifier
            )
        }
        if (!is24Hour) {
            AmPmPicker(
                selectedItem = meridiem,
                onItemSelected = { meridiem ->
                    selectedHour = if (meridiem == Meridiem.PM && selectedHour < 12) selectedHour + 12
                    else if (meridiem == Meridiem.AM && selectedHour >= 12) selectedHour - 12
                    else selectedHour
                    onTimeChanged(LocalTime(selectedHour, selectedMinute))
                },
                itemHeight = itemHeight,
                modifier = modifier
            )
        }
    }
}

//@Preview
//@Composable
//fun WheelTimePickerPreview() {
//    // Start with a sample time (7:30)
//    var previewTime by remember { mutableStateOf(LocalTime(7, 30)) }
//
//    WakeUpAppTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            WheelTimePickerWrapper(
//                time = previewTime,
//                onTimeChanged = { previewTime = it } // updates local state so preview stays reactive
//            )
//        }
//    }
//}
