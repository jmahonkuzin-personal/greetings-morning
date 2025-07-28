package com.example.hopouttabed.addAlarm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalTime

@Composable
fun TimePickerSection(time: MutableState<LocalTime>, isAm: MutableState<Boolean>) {
    // Use your own implementation or 3rd-party picker for a real time picker.
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            "${time.value.hour} : ${time.value.minute}",
            style = MaterialTheme.typography.displayMedium,
            color = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        DropdownMenuSelector(
            options = listOf("AM", "PM"),
            selected = if (isAm.value) "AM" else "PM",
            onSelect = { isAm.value = it == "AM" }
        )
    }
}
