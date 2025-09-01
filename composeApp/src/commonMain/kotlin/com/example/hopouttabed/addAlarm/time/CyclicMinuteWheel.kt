package com.example.hopouttabed.addAlarm.time


import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.roundToInt

@Composable
fun CyclicMinuteWheel(
    minute: Int,
    onMinuteSelected: (Int) -> Unit,
    itemHeight: Dp = 56.dp,
    visibleCount: Int = 5 // must be odd
) {
    require(visibleCount % 2 == 1)
    val size = 20
    val repeats = 3                     // small, enough to wrap seamlessly
    val total = size * repeats          // 300 rows
    val centerSlot = visibleCount / 2
    val wheelHeight = itemHeight * visibleCount

    // Start in the middle block so you can scroll both ways
    val midBlock = repeats / 2
    val startIndex = (midBlock * size) - centerSlot + (minute % size)

    val state = rememberLazyListState(initialFirstVisibleItemIndex = startIndex)
    val fling = rememberSnapFlingBehavior(state)

    // Recenter when drifting near edges (instant jump; user won't notice)
    LaunchedEffect(state) {
        snapshotFlow { state.firstVisibleItemIndex }
            .collect { idx ->
                val edge = size // one block from either side
                when {
                    idx < edge -> state.scrollToItem(idx + size * 2)
                    idx > total - edge -> state.scrollToItem(idx - size * 2)
                }
            }
    }

    // Report centered minute when scroll settles
    val density = LocalDensity.current
    val pxItem = with(density) { itemHeight.toPx() }
    LaunchedEffect(state.isScrollInProgress) {
        if (!state.isScrollInProgress) {
            val offsetItems = (state.firstVisibleItemScrollOffset / pxItem).roundToInt()
            val centerIndex = state.firstVisibleItemIndex + centerSlot + offsetItems
            val value = ((centerIndex % size) + size) % size
            onMinuteSelected(value)
        }
    }

    // UI
    Box(
        modifier = Modifier.requiredHeight(wheelHeight),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            state = state,
            flingBehavior = fling,
            modifier = Modifier.requiredHeight(wheelHeight),
            contentPadding = PaddingValues(vertical = itemHeight * centerSlot),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 0..59 repeated 'repeats' times: …58, 59, 00, 01…
            items(count = total, key = { it }) { i ->
                val value = i % size
                Text(
                    text = value.toString().padStart(2, '0'),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .height(itemHeight)
                        .fillMaxWidth()
                        .clickable {
                            // optional: center the tapped row
                            // LaunchedEffect(Unit) { state.animateScrollToItem(i) }
                            onMinuteSelected(value)
                        }
                )
            }
        }
    }
}

@Preview
@Composable
fun CyclicMinuteWheelPreview() {
    var selected by remember { mutableStateOf(17) }
    MaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(24.dp)
        ) {
            Text("Selected: $selected", modifier = Modifier.padding(bottom = 16.dp))
            CyclicMinuteWheel(
                minute = selected,
                onMinuteSelected = { selected = it },
                itemHeight = 56.dp,
                visibleCount = 3
            )
        }
    }
}
