package com.example.hopouttabed

// commonMain/kotlin/com/example/common/AppInfoProvider.kt
expect class AppInfoProvider {
    fun getAllInstalledApps(): List<ApplicationInfo>
}

data class ApplicationInfo(val packageName: String, val appName: String, val icon: Any?)