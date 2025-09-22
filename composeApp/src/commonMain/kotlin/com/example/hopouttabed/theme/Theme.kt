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

val Surface        = Color(0xFF17191C) // dialog/card
val SurfaceVariant = Color(0xFF3C4047) // chips, unselected states
val OnSurface      = Color(0xFFE6E6E6) // primary text
val OnSurfaceVar   = Color(0xFFC8CCD2) // secondary text on chips
val Outline        = Color(0xFF4F5359) // subtle borders


val DarkColorScheme = darkColorScheme(
    primary = SoftPink,       // Soft pink accent
    onPrimary = PureBlack,     // Text on pink (black)

    background = DeepBlack,    // App background
    onBackground = PureWhite,  // Text on background (white)

    surface = Surface,
    onSurface = OnSurface,

    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = OnSurfaceVar,

    secondary = Gunmetal,
    onSecondary = PureWhite.copy(alpha = 0.9f),

    secondaryContainer = DarkGray,
    onSecondaryContainer = PureWhite,

    tertiary = Color(0xFF689f95),      // Calming teal for optional accent
    onTertiary = Color(0xFF000000),    // Text/icon on teal

    error = Color(0xFFD75D5D),         // Muted red for errors
    onError = Color(0xFFFFFFFF),       // Text/icon on error bg

    outline = Outline        // Border and dividers (subtle gray)
)
