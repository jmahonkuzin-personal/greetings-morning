package com.example.hopouttabed.edit.basicAlarmSettings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog


@Composable
fun TheWhyOfTheAlarmSettings() {
    // State to hold the user's input
    var taskText by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .wrapContentWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        var showExplanation by remember { mutableStateOf(false) }

        val inlineContentId = "infoIcon"

        val text = buildAnnotatedString {
            append("A message to repeat when this alarm goes off ")
            appendInlineContent(inlineContentId, "[info]")
        }

        val inlineContent = mapOf(
            inlineContentId to InlineTextContent(
                placeholder = Placeholder(
                    width = 20.sp,
                    height = 20.sp,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Information",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .clickable { showExplanation = true }
                        .size(18.dp)
                )
            }
        )

        Text(
            text = text,
            inlineContent = inlineContent,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )

        // --- Info popup dialog ---
        if (showExplanation) {
            Dialog(onDismissRequest = { showExplanation = false }) {
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.surface,
                    tonalElevation = 6.dp,
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Why set a message?",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "This message will be displayed when your alarm goes off. It helps remind you of your goals and motivation for waking up early.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        TextButton(
                            onClick = { showExplanation = false },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("Got it")
                        }
                    }
                }
            }
        }

        if (showExplanation) {
            Dialog(onDismissRequest = { showExplanation = false }) {
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Why set a message?",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "This message will be displayed when your alarm goes off. It helps remind you of your goals and motivation for waking up early.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        TextButton(
                            onClick = { showExplanation = false },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("Got it")
                        }
                    }
                }
            }
        }


        // Text box for input
        OutlinedTextField(
            value = taskText,
            onValueChange = { newText -> taskText = newText },
            label = { Text("Your task") },
            placeholder = { Text("Write down your plans...") },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 120.dp),
            shape = MaterialTheme.shapes.extraSmall,
            singleLine = false,
            maxLines = 4 // Allowing multiline input if needed
        )

        // Spacing and potential additional UI elements
        Spacer(modifier = Modifier.height(20.dp))

        // Optionally display the entered text
        if (taskText.text.isNotEmpty()) {
            Text(
                text = "You wrote: \"${taskText.text}\"",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
//
//@Composable
//fun TheWhyOfTheAlarmSetting(
////    switchState: Boolean,
////    onToggle: (Boolean) -> Unit,
//) {
//    var switchState = true
//    val onToggle = { switchVal : Boolean -> switchState = !switchVal }
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(50.dp)
//            .background(
//                color = MaterialTheme.colorScheme.secondary,
//                shape = MaterialTheme.shapes.medium
//            )
//            .padding(horizontal = 16.dp, vertical = 12.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            text = "The Why (?)",
//            color = MaterialTheme.colorScheme.onSecondary,
//            style = MaterialTheme.typography.titleLarge
//        )
//        Spacer(modifier = Modifier.weight(1f)) // 👈 Push content to the right
//
//        Switch(
//            checked = switchState,
//            onCheckedChange = onToggle,
//            colors = SwitchDefaults.colors(
//                checkedThumbColor = Color.White,
//                checkedTrackColor = MaterialTheme.colorScheme.primary,
//                uncheckedThumbColor = Color.LightGray,
//                uncheckedTrackColor = Color.Gray
//            ),
//        )
//    }
//}
