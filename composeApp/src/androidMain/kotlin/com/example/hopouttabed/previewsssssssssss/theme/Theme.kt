package com.example.hopouttabed.previewsssssssssss.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun WakeUpAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = rememberWakeUpAppTypography(),
        shapes = WakeUpShapes,
        content = content,
    )
}

val DarkColorScheme = darkColorScheme(
    primary = SoftPink,       // Soft pink accent
    onPrimary = PureBlack,     // Text on pink (black)

    background = DeepBlack,    // App background
    onBackground = PureWhite,  // Text on background (white)

    surface = MidGray,       // Task card background
    onSurface = PureBlack,     // Text on card

    secondary = Gray,     // Activity bubble fill
    onSecondary = PureBlack,   // Icon/text on bubbles
    secondaryContainer = DarkGray,   // Disabled

    tertiary = Color(0xFF8AD2C5),      // Calming teal for optional accent
    onTertiary = Color(0xFF000000),    // Text/icon on teal

    error = Color(0xFFD75D5D),         // Muted red for errors
    onError = Color(0xFFFFFFFF),       // Text/icon on error bg

    outline = Color(0xFF4D4D4D)        // Border and dividers (subtle gray)
)
