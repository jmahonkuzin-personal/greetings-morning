package com.example.hopouttabed

import platform.Foundation.NSUUID

@Suppress(names = ["EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING"])
actual fun randomUUID(): String = NSUUID().UUIDString()
