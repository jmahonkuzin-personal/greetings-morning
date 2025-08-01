package com.example.hopouttabed.addAlarm.basicAlarmSettings

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hopouttabed.AppBackHandler
import com.example.hopouttabed.addAlarm.AddAlarmScreen
import com.example.hopouttabed.addAlarm.sound.AlarmSoundPickerScreen
import com.example.hopouttabed.addAlarm.viewModel.AlarmSoundViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun AddAlarmPagerScreen(
    onSave: () -> Unit,
    onBackConfirmed: () -> Unit
) {
    val pageCount = 2
    val pagerState = rememberPagerState(pageCount = { pageCount })
    val coroutineScope = rememberCoroutineScope()

    val alarmSoundVm: AlarmSoundViewModel = viewModel()
    val alarmSoundUiState by alarmSoundVm.uiState.collectAsStateWithLifecycle()

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        when (page) {
            0 -> {
                AddAlarmScreen(
                    onSave = onSave,
                    onBackConfirmed = onBackConfirmed,
                    onNavigateToSoundPicker = {
                        coroutineScope.launch { pagerState.animateScrollToPage(1) }
                    },
                    alarmSoundUiState = alarmSoundUiState
                )

                AppBackHandler {
                    onBackConfirmed()
                }
            }

            1 -> {
                AlarmSoundPickerScreen(
                    onBack = {
                        coroutineScope.launch { pagerState.animateScrollToPage(0) }
                    },
                    alarmSoundUiState = alarmSoundUiState,
                    onSoundSelection = alarmSoundVm::updateSound
                )

                AppBackHandler {
                    coroutineScope.launch { pagerState.animateScrollToPage(0) }
                }
            }
        }
    }
}
