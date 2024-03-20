package com.profile.changer.domain.repositories

import com.profile.changer.domain.entities.DeviceProfile
import kotlinx.coroutines.flow.Flow
import kotlin.properties.Delegates

interface ProfileRepository {

    val all: List<DeviceProfile>

    suspend fun getActiveProfile(): DeviceProfile?

    suspend fun setActiveProfile(profile: DeviceProfile): Boolean

    suspend fun removeActiveProfile()

}