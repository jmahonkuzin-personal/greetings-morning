package com.example.hopouttabed

import android.content.Context
import android.content.pm.PackageManager

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class AppInfoProvider(private val context: Context) {
    actual fun getAllInstalledApps(): List<ApplicationInfo> {
        val packageManager: PackageManager = context.packageManager
        val installedApps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        return installedApps.map { appInfo ->
            ApplicationInfo(
                packageName = appInfo.packageName,
                appName = packageManager.getApplicationLabel(appInfo).toString(),
                icon = packageManager.getApplicationIcon(appInfo) // Handle icon conversion as needed
            )
        }
    }
}