package com.example.hopouttabed

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform