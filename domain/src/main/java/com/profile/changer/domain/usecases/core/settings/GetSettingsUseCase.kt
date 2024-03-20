package com.profile.changer.domain.usecases.core.settings

import com.profile.changer.domain.repositories.ProfileRepository
import com.profile.changer.domain.repositories.SettingsRepository
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
){

    operator fun invoke() = settingsRepository.settings

}