package com.profile.changer.domain.entities

data class DeviceProfile(
    val name: String,
    val fileName: String,
    val properties: Map<String, String>
)
