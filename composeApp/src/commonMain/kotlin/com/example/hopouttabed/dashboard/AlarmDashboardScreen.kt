package com.example.hopouttabed.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hopouttabed.dashboard.viewModel.DashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmDashboardScreen(
    onAddAlarmClick: () -> Unit,
    dashboardViewModel: DashboardViewModel = viewModel()
) {

    val alarms by dashboardViewModel.alarms.collectAsState()

    Scaffold(
        topBar = {
            DashboardTopAppBar()
        },
        floatingActionButton = {
            AddAlarmFloatingActionButton(
                onAddAlarmClick = onAddAlarmClick
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            BedtimeSection()
            Spacer(modifier = Modifier.height(24.dp))
            AlarmList(
                alarms = alarms,
                onToggle = dashboardViewModel::toggleAlarm
            )
        }
    }
}
