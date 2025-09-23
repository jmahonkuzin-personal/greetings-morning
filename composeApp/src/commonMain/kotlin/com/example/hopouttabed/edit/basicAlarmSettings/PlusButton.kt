package com.example.hopouttabed.edit.basicAlarmSettings


import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.ripple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AttentionPlusButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 64.dp,
    enabled: Boolean = true,
    tint: Color = MaterialTheme.colorScheme.onPrimary,
    container: Color = MaterialTheme.colorScheme.primary
) {
    // subtle halo pulse to say “tap me”
    val pulseTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by pulseTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )
    val pulseAlpha by pulseTransition.animateFloat(
        initialValue = 0.18f,
        targetValue = 0.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseAlpha"
    )

    var pressed by remember { mutableStateOf(false) }
    val pressScale = if (pressed) 0.97f else 1f

    Box(
        modifier = modifier
            .size(size)
            .semantics {
                contentDescription = "Add"
                role = Role.Button
            },
        contentAlignment = Alignment.Center
    ) {
        // halo
        Box(
            Modifier
                .matchParentSize()
                .scale(pulseScale)
                .background(container, CircleShape)
                .alpha(pulseAlpha)
        )

        // the button itself (only a plus)
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .scale(pressScale)
                .clickable(
                    enabled = enabled,
                    role = Role.Button,
                    indication = ripple(
                        bounded = true,
                        radius = size / 2
                    ),
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = onClick,
                    onClickLabel = "Add"
                ),
            shape = CircleShape,
            color = container,
            tonalElevation = 6.dp,
            shadowElevation = 8.dp
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null, // already provided via semantics above
                    tint = tint
                )
            }
        }
    }

    // lightweight pressed-state handling using touch slop
    // (optional, but nice for the micro “bounce”)
    LaunchedEffect(Unit) {
        // no-op placeholder so preview compiles cleanly
    }
}

@Preview
@Composable
private fun AttentionPlusButtonPreview() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AttentionPlusButton(onClick = {})
    }
}
