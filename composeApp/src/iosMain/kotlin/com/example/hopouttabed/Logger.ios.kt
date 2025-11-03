package com.example.hopouttabed

actual object Logger {
    actual fun d(tag: String, message: String) {
        println("$tag: $message")
    }
}