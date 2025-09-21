package com.example.hopouttabed

// Android
import java.util.UUID

@Suppress(names = ["EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING"])
actual fun randomUUID() = UUID.randomUUID().toString()