package com.example.hopouttabed.addAlarm.time

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.example.hopouttabed.addAlarm.viewModel.Meridiem
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@Composable
fun AmPmPicker(
    selectedItem: Meridiem,
    onItemSelected: (Meridiem) -> Unit,
    itemHeight: Dp,
    modifier: Modifier
) {
    val items = listOf(Meridiem.AM, Meridiem.PM)
    val listState = rememberLazyListState()
    val fling = rememberSnapFlingBehavior(listState)
    val visibleItems = 3
    val selectedIndex = remember { mutableIntStateOf(items.indexOf(selectedItem)) }
    val density = LocalDensity.current

    val coroutineScope = rememberCoroutineScope()

    // Drive color from scroll state (this is State, so recomposes)
    val inActionColor = MaterialTheme.colorScheme.primary
    val idleColor = MaterialTheme.colorScheme.onBackground
    val isScrolling by remember { derivedStateOf { listState.isScrollInProgress } }
    val textColor by animateColorAsState(
        targetValue = if (isScrolling) inActionColor else idleColor,
        label = "scrollColor"
    )
    val textFontSize = 50.sp

    // When scrolling stops, compute the centered index and notify if changed
    LaunchedEffect(isScrolling) {
        if (!isScrolling) {
            val firstItemOffset = listState.firstVisibleItemScrollOffset
            val pxItemHeight = with(density) { itemHeight.toPx() }
            val offsetItems = (firstItemOffset / pxItemHeight).roundToInt()
            val centerIndex = (1 + offsetItems).coerceIn(0, 1)

            val centered = items[centerIndex]
            if (centered != selectedItem) {
                onItemSelected(centered)
            }
        }
    }

    LazyColumn(
        state = listState,
        modifier = modifier,
        flingBehavior = fling,
        contentPadding = PaddingValues(vertical = itemHeight * (visibleItems / 2))
    ) {
        items(items.size) { i ->
            val item = items[i]
            Text(
                text = item.toString(),
                color = textColor,
                fontSize = textFontSize,
                modifier = Modifier
                    .clickable {
                        coroutineScope.launch {
                            listState.animateScrollToItem(i)
                        }
                        onItemSelected(item)
                    }
            )
        }
    }
}
