package com.example.hopouttabed.theme

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

val DeepBlack = Color(0xFF0E0E0E) // Background
val MidGray = Color(0xFFBFBFBF) // Task panels/boxes
//val SoftPink = Color(0xFFF6AEB2) // Accent (task check ✅)
val SoftPink = Color(0xFFD9A5B3) // Accent (task check ✅)
val PureWhite = Color(0xFFFFFFFF) // Buttons (text/borders)
val PureBlack = Color(0xFF000000) // Icons/Text (main)
val Gray = Color(0xFFA3A3A3) // Activity circles -- not sure if we should use this or MidGray for secondary
val Gunmetal = Color(0xFF2A2E35)
val LighterGunmetal = Color(0xFF3c4047)
val EvenLighterGunmetal = Color(0xFF4f5359)
val DarkGray = Color(0xFF2A2A2A) // text fields, modal surfaces, or inactive buttons.


val DarkColorScheme = darkColorScheme(
    primary = SoftPink,       // Soft pink accent
    onPrimary = PureBlack,     // Text on pink (black)

    background = DeepBlack,    // App background
    onBackground = PureWhite,  // Text on background (white)

    surface = MidGray,       // Task card background
    onSurface = PureBlack,     // Text on card

    secondary = Gunmetal,
    onSecondary = PureWhite,

    secondaryContainer = DarkGray,
    onSecondaryContainer = PureWhite,

    tertiary = Color(0xFF8AD2C5),      // Calming teal for optional accent
    onTertiary = Color(0xFF000000),    // Text/icon on teal

    error = Color(0xFFD75D5D),         // Muted red for errors
    onError = Color(0xFFFFFFFF),       // Text/icon on error bg

    outline = Color(0xFF4D4D4D)        // Border and dividers (subtle gray)
)
