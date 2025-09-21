package com.example.hopouttabed.viewModel


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