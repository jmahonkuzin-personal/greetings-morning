package com.example.hopouttabed

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hopouttabed.addAlarm.basicAlarmSettings.AddAlarmPagerScreen
import com.example.hopouttabed.dashboard.AlarmDashboardScreen

@Composable
fun AlarmNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") {
            AlarmDashboardScreen(
                onAddAlarmClick = { navController.navigate("addAlarmPager") }
            )
        }
        composable("addAlarmPager") {
            AddAlarmPagerScreen(
                navigateHome = { navController.navigate("dashboard") },
                onBackConfirmed = { navController.popBackStack() }
            )
        }
    }
}
