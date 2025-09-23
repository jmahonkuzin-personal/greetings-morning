package com.example.hopouttabed.edit.time

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalTime


@Composable
fun TimeSummaryStatic(
    time: LocalTime?,                  // null = no time set
    modifier: Modifier = Modifier,
    onEdit: () -> Unit
) {
    val hh = time?.let { val h12 = ((it.hour + 11) % 12) + 1; h12.toString().padStart(2, '0') } ?: "--"
    val mm = time?.minute?.toString()?.padStart(2, '0') ?: "--"
    val isAm = time?.hour?.let { it < 12 } ?: true
    val amPmText = if (isAm) "AM" else "PM"

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.medium
            )
            .clickable(onClick = onEdit)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.medium)
            .padding(horizontal = 16.dp, vertical = 32.dp)

    ) {
        Text(
            text = "$hh:$mm $amPmText",
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Center)
        )
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = modifier.align(Alignment.CenterEnd)
        )
    }
}
