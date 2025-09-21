package com.example.hopouttabed.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.hopouttabed.composeapp.generated.resources.Res
import com.example.hopouttabed.composeapp.generated.resources.roboto_mono_bold
import com.example.hopouttabed.composeapp.generated.resources.roboto_mono_regular
import org.jetbrains.compose.resources.Font


@Composable
fun robotoMonoFontFamily(): FontFamily {
    return FontFamily(
        Font(resource = Res.font.roboto_mono_regular, weight = FontWeight.Normal),
        Font(resource = Res.font.roboto_mono_bold, weight = FontWeight.Bold)
    )
}

@Composable
fun rememberWakeUpAppTypography(): Typography {
    val robotoMono = robotoMonoFontFamily()

    return Typography(
        displayLarge = TextStyle(fontSize = 34.sp, fontWeight = FontWeight.Bold, fontFamily = robotoMono),
        displayMedium = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold, fontFamily = robotoMono),
        headlineLarge = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, fontFamily = robotoMono),
        headlineMedium = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, fontFamily = robotoMono),
        headlineSmall = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium, fontFamily = robotoMono),
        titleLarge = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, fontFamily = robotoMono),
        bodyLarge = TextStyle(fontSize = 16.sp, fontFamily = robotoMono),
        bodyMedium = TextStyle(fontSize = 14.sp, fontFamily = robotoMono),
        labelLarge = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold, fontFamily = robotoMono)
    )
}