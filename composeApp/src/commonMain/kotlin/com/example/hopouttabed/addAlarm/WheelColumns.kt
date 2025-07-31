package com.example.hopouttabed.addAlarm

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun InfiniteWheelColumn(
    items: List<Int>, // e.g. (1..12).toList()
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val itemHeight = 64.dp
    val visibleItems = 3
    val itemCount = items.size
    val loopMultiplier = 1000 // large number to simulate infinity
    val loopedItems = remember { List(itemCount * loopMultiplier) { i -> items[i % itemCount] } }
    val startIndex = (loopedItems.size / 2).let {
        // center on selectedItem
        val selectedOffset = items.indexOf(selectedItem)
        it - (it % itemCount) + selectedOffset
    }

    val listState = rememberLazyListState(startIndex)
    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current

    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
            val index = listState.firstVisibleItemIndex
            val offset = listState.firstVisibleItemScrollOffset
            val pxItemHeight = with(density) { itemHeight.toPx() }

            val shouldSnapTo = if (offset > pxItemHeight / 2) index + 1 else index
            val selected = loopedItems[shouldSnapTo % loopedItems.size]

            onItemSelected(selected)

            coroutineScope.launch {
                listState.animateScrollToItem(shouldSnapTo)
            }
        }
    }

    LazyColumn(
        state = listState,
        modifier = modifier
            .height(itemHeight * visibleItems)
            .width(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(vertical = itemHeight * (visibleItems / 2))
    ) {
        items(loopedItems.size) { i ->
            val value = loopedItems[i]
            val isSelected = value == selectedItem

            Text(
                text = value.toString().padStart(2, '0'),
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.White,
                fontSize = 35.sp,
                modifier = Modifier
                    .height(itemHeight)
                    .fillMaxWidth()
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

@Composable
fun <T> WheelColumn(
    items: List<T>,
    selectedItem: T,
    onItemSelected: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val itemHeight = 64.dp
    val visibleItems = 3
    val selectedIndex = remember { mutableIntStateOf(items.indexOf(selectedItem)) }
    val density = LocalDensity.current

    // Snap when scrolling ends
    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
            val index = listState.firstVisibleItemIndex
            val offset = listState.firstVisibleItemScrollOffset
            val pxItemHeight = with(density) { itemHeight.toPx() }

            val shouldSnapTo = if (offset > pxItemHeight / 2) index + 1 else index

            if (shouldSnapTo in items.indices) {
                val newValue = items[shouldSnapTo]
                selectedIndex.value = shouldSnapTo
                onItemSelected(newValue)

                coroutineScope.launch {
                    listState.animateScrollToItem(shouldSnapTo)
                }
            }
        }
    }

    LazyColumn(
        state = listState,
        modifier = modifier.height(itemHeight * visibleItems).width(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(vertical = itemHeight * (visibleItems / 2))
    ) {
        items(items) { item ->
            val isSelected = item == selectedItem
            Text(
                text = item.toString().padStart(2, '0'),
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.White,
                fontSize = 35.sp,
                modifier = Modifier
                    .height(itemHeight)
                    .fillMaxWidth()
                    .clickable { onItemSelected(item) },
            )
        }
    }
}

