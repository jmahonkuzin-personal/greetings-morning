package com.example.hopouttabed.addAlarm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun MissionSection(
    missionStates: SnapshotStateList<Boolean>,
    icons: List<ImageVector>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.DarkGray, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text("Mission", color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            icons.forEachIndexed { index, icon ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Checkbox(
                        checked = missionStates.getOrNull(index) == true,
                        onCheckedChange = { missionStates[index] = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFFFFB6B6),
                            checkmarkColor = Color.Black
                        )
                    )
                    Icon(icon, contentDescription = null, tint = Color.White)
                }
            }
        }
    }
}
