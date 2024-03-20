package com.profile.changer.domain.usecases.core.settings

import com.profile.changer.domain.entities.Settings
import com.profile.changer.domain.repositories.SettingsRepository
import javax.inject.Inject

class SetSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    operator fun invoke(settings: Settings) {
        settingsRepository.settings = settings
    }

}