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
import com.example.hopouttabed.viewModel.AlarmCallbacks
import com.example.hopouttabed.viewModel.AlarmViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun AddAlarmPagerScreen(
    navigateHome: () -> Unit,
    onBackConfirmed: () -> Unit
) {
    val pageCount = 2
    val pagerState = rememberPagerState(pageCount = { pageCount })
    val coroutineScope = rememberCoroutineScope()

    val alarmVM: AlarmViewModel = viewModel()
    val alarmUiState by alarmVM.uiState.collectAsStateWithLifecycle()
    val alarmCallbacks: AlarmCallbacks = AlarmCallbacks(
        updateTime = alarmVM::updateTime,
        updateDisabledMinutes = alarmVM::updateDisabledMinutes,
        updateAllowedAppsDuringDisable = alarmVM::updateAllowedAppsDuringDisable,
        toggleVibrate = alarmVM::toggleVibrate,
        toggleSnooze = alarmVM::toggleSnooze,
        updateSound = alarmVM::updateSound
    )

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        when (page) {
            0 -> {
                AddAlarmScreen(
                    onSave = {
                        alarmVM.saveAlarm()
                        navigateHome
                    },
                    onBackConfirmed = onBackConfirmed,
                    onNavigateToSoundPicker = {
                        coroutineScope.launch { pagerState.animateScrollToPage(1) }
                    },
                    alarmUiState = alarmUiState,
                    alarmCallbacks = alarmCallbacks
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
                    alarmUiState = alarmUiState,
                    onSoundSelection = alarmCallbacks.updateSound
                )

                AppBackHandler {
                    coroutineScope.launch { pagerState.animateScrollToPage(0) }
                }
            }
        }
    }
}
