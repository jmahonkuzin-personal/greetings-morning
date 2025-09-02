package com.example.hopouttabed

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hopouttabed.addAlarm.AddAlarmScreen
import com.example.hopouttabed.addAlarm.basicAlarmSettings.AddAlarmPagerScreen
import com.example.hopouttabed.addAlarm.viewModel.AlarmCallbacks
import com.example.hopouttabed.addAlarm.viewModel.AlarmViewModel
import com.example.hopouttabed.theme.WakeUpAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
//    // Mock state
//    val pageCount = 2
//    val pagerState = rememberPagerState(pageCount = { pageCount })
//    val coroutineScope = rememberCoroutineScope()
//
//    val alarmVM: AlarmViewModel = viewModel()
//    val alarmUiState by alarmVM.uiState.collectAsStateWithLifecycle()
//    val alarmCallbacks: AlarmCallbacks = AlarmCallbacks(
//        updateTime = alarmVM::updateTime,
//        updateDisabledMinutes = alarmVM::updateDisabledMinutes,
//        updateAllowedAppsDuringDisable = alarmVM::updateAllowedAppsDuringDisable,
//        toggleVibrate = alarmVM::toggleVibrate,
//        toggleSnooze = alarmVM::toggleSnooze,
//        updateSound = alarmVM::updateSound
//    )

    WakeUpAppTheme {
        AlarmNavHost()
    }
}