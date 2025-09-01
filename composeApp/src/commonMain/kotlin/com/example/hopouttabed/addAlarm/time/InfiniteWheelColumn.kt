package com.example.hopouttabed.addAlarm.time

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun InfiniteWheelColumn(
    items: List<Int>, // e.g. (1..12).toList()
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    itemHeight: Dp
) {
//    val itemHeight = 64.dp
    val visibleItems = 3
    val columnHeight = itemHeight * visibleItems
    val itemCount = items.size

    val loopMultiplier = 1000 // large number to simulate infinity
    val loopedItems = remember { List(itemCount * loopMultiplier) { i -> items[i % itemCount] } }
    val startIndex = (loopedItems.size / 2).let {
        // center on selectedItem
        val selectedOffset = items.indexOf(selectedItem)
        it - (it % itemCount) + selectedOffset
    }

    val listState = rememberLazyListState(startIndex)
    val fling = rememberSnapFlingBehavior(listState)

    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current
//    val textColor = MaterialTheme.colorScheme.onBackground
    val textFontSize = 50.sp

    // Drive color from scroll state (this is State, so recomposes)
    val inActionColor = MaterialTheme.colorScheme.primary
    val idleColor = MaterialTheme.colorScheme.onBackground
    val isScrolling by remember { derivedStateOf { listState.isScrollInProgress } }
    val textColor by animateColorAsState(
        targetValue = if (isScrolling) inActionColor else idleColor,
        label = "scrollColor"
    )

    val mid by remember {
        derivedStateOf {
            val visibleItems = listState.layoutInfo.visibleItemsInfo
            if (visibleItems.isEmpty()) {
                1
            } else {
                visibleItems.getOrNull(visibleItems.size / 2)?.index // return index of middle visible item
            }
        }
    }

    LaunchedEffect(listState.isScrollInProgress) {

        if (!listState.isScrollInProgress) {
//            val firstItemOffset = listState.firstVisibleItemScrollOffset
//            val firstItemIndex = listState.firstVisibleItemIndex
//            val pxItemHeight = with(density) { itemHeight.toPx() }
//            val offsetItems = (firstItemOffset / pxItemHeight).roundToInt()
// Only need to hop between the top item and the one below it:
//            val centerIndex = (/* firstVisibleItemIndex = 0 */ + 1 /* centerSlot */ + offsetItems)
//                .coerceIn(0, 1)
            // approximate how many items scrolled within the first visible row
//            val offsetItems = (firstItemOffset / pxItemHeight).roundToInt()
//            val centerIndex = (firstItemIndex + centerSlot + offsetItems)
//                .coerceIn(0, items.lastIndex)

            val centerIndex = mid ?: 1
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
//        items(items = loopedItems.size, key = { it }) { i ->
        items(loopedItems.size) { i ->
            val value = loopedItems[i]
            Text(
                text = value.toString().padStart(2, '0'),
                color = textColor,
                fontSize = textFontSize,
                modifier = Modifier
                    .clickable {
                        coroutineScope.launch {
                            listState.animateScrollToItem(i)
                        }
                        onItemSelected(value)
                    }
            )
        }
    }
}

