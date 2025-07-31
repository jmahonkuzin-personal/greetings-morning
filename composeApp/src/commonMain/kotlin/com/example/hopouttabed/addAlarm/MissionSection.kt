package com.example.hopouttabed.addAlarm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MissionSection() {
    // 3 mission states, matching the 3 icons
    val missionStates = remember {
        mutableStateListOf(true, false, false)
    }

    val icons = listOf(
        Icons.Default.WbSunny,   // Light
        Icons.Default.Campaign,  // Megaphone
        Icons.Default.GraphicEq  // Sound wave
    )

//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//            .background(Color.DarkGray, shape = RoundedCornerShape(12.dp))
//            .padding(16.dp)
//    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(vertical = 8.dp)
            .background(Color.DarkGray, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Mission", color = Color.White)
        Spacer(modifier = Modifier.weight(1f)) // 👈 Push content to the right


        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icons.forEachIndexed { index, icon ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = missionStates[index],
                        onCheckedChange = { missionStates[index] = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFFFFB6B6),
                            checkmarkColor = Color.Black,
                            uncheckedColor = Color.LightGray
                        )
                    )
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}
