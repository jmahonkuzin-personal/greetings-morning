package com.example.hopouttabed.addAlarm.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


enum class AlarmSound(val displayName: String) {
    BeepBeep("Beep Beep"),
    Beacon("Beacon"),
    Radar("Radar"),
    BellRing("Bell Ring"),
    SunriseChimes("Sunrise Chimes"),
    Uplift("Uplift"),
    DreamPop("Dream Pop"),
    HappyVibes("Happy Vibes"),
    Birdsong("Birdsong"),
    OceanWaves("Ocean Waves"),
    Rainfall("Rainfall"),
    SirenSprint("Siren Sprint"),
    SoftGong("Soft Gong"),
    CosmicHum("Cosmic Hum"),
    GameStart("Game Start");

    override fun toString(): String = displayName

    companion object {
        fun fromDisplayName(name: String): AlarmSound? =
            entries.firstOrNull { it.displayName == name }
    }
}