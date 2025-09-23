package com.example.hopouttabed


@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class AppInfoProvider {
    actual fun getAllInstalledApps(): List<ApplicationInfo> {
        // Cannot access other installed apps on iOS due to security restrictions
        return emptyList()
    }
}