package com.example.hopouttabed

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import kotlin.time.Duration.Companion.seconds

@Composable
fun AlarmDemoScreen() {
    val scheduler = rememberAlarmScheduler()
    var lastId by remember { mutableStateOf<String?>(null) }
    var secondsFromNow by remember { mutableStateOf(1) } // simple demo control

    Column(Modifier.padding(16.dp)) {
        Text("Alarm demo (KMP commonMain)")

        Spacer(Modifier.height(12.dp))

        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Button(onClick = {
                // schedule an alarm N seconds from now
                val id = "demo_${Clock.System.now().toEpochMilliseconds()}"
                val triggerAt = (Clock.System.now() + secondsFromNow.seconds).toEpochMilliseconds()
                scheduler.schedule(
                    alarmId = id,
                    epochMillis = triggerAt,
                    title = "Wake up!",
                    body = "Compose Multiplatform alarm in $secondsFromNow seconds"
                )
                lastId = id
            }) { Text("Schedule in ${secondsFromNow}s") }

            Spacer(Modifier.width(12.dp))

            Button(
                enabled = lastId != null,
                onClick = { lastId?.let { scheduler.cancel(it); lastId = null } }
            ) { Text("Cancel last") }
        }

        Spacer(Modifier.height(12.dp))

        // Tiny +/- to tweak delay for testing
        Row {
            Button(onClick = { if (secondsFromNow > 1) secondsFromNow-- }) { Text("-") }
            Spacer(Modifier.width(8.dp))
            Text("$secondsFromNow s")
            Spacer(Modifier.width(8.dp))
            Button(onClick = { secondsFromNow++ }) { Text("+") }
        }
    }
}
