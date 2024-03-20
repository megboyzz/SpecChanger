package com.profile.changer.domain.entities

data class Settings(
    val autoChangeProfile: Boolean,
    val changeIntervalInHours: Int
)
