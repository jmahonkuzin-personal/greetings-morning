package com.example.hopouttabed.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

internal val WakeUpShapes = Shapes(
    // Extremely rounded shapes (e.g. FABs, bubble-style backgrounds)
    extraLarge = RoundedCornerShape(4.dp),

    // Used for large buttons, containers, or modals
    large = RoundedCornerShape(8.dp),

    // Most common shape for cards, panels, and components
    medium = RoundedCornerShape(12.dp),

    // Small buttons or input fields
    small = RoundedCornerShape(24.dp),

    // Tiny elements like switches or chips
    extraSmall = RoundedCornerShape(32.dp)
)