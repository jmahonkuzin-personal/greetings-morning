package com.example.hopouttabed.addAlarm.time

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hopouttabed.theme.WakeUpAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.roundToInt

@Composable
fun EndlessList(
    listState: LazyListState = rememberLazyListState(),
    threshold: Int = 5,
    loadMore: suspend () -> Boolean,
    content: LazyListScope.() -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }
    var endReached by remember { mutableStateOf(false) }

    LaunchedEffect(listState, threshold) {
        snapshotFlow {
            val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            val total = listState.layoutInfo.totalItemsCount
            lastVisible != null && total > 0 && lastVisible >= total - threshold
        }
            .distinctUntilChanged()
            .collect { shouldLoad ->
                if (shouldLoad && !isLoading && !endReached) {
                    isLoading = true
                    try {
                        val gotMore = loadMore()
                        if (!gotMore) endReached = true
                    } finally {
                        isLoading = false
                    }
                }
            }
    }

    LazyColumn(state = listState) {
        content() // your list rows

        if (isLoading && !endReached) {
            item { LoadingRow() }
        }
        if (endReached) {
            item { EndOfListRow() }
        }
    }
}


@Composable
fun LoadingRow() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun EndOfListRow() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("— End of list —")
    }
}


@Preview
@Composable
fun EndlessListPreview() {
    // Fake list state
    var items by remember { mutableStateOf((0 until 20).toMutableList()) }
    val listState = rememberLazyListState()

    // Fake "network call"
    suspend fun loadMore(): Boolean {
        delay(1000) // pretend to fetch from server
        return if (items.size < 60) { // 3 pages of 20
            val nextStart = items.size
            items = (items + (nextStart until nextStart + 20)).toMutableList()
            true
        } else {
            false // no more data
        }
    }

    WakeUpAppTheme {
        EndlessList(
            listState = listState,
            threshold = 3,
            loadMore = { loadMore() }
        ) {
            items(items.size) { i ->
                val value = items[i]
            // Pass actual list rows
//            items(items) { value ->
                Text(
                    text = "Item $value",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}
