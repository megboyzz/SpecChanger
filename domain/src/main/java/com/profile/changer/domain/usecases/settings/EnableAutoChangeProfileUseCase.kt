package com.profile.changer.domain.usecases.settings

import com.profile.changer.domain.usecases.core.settings.GetSettingsUseCase
import com.profile.changer.domain.usecases.core.settings.SetSettingsUseCase
import javax.inject.Inject

class EnableAutoChangeProfileUseCase @Inject constructor(
    private val setSettingsUseCase: SetSettingsUseCase,
    private val getSettingsUseCase: GetSettingsUseCase
) {

    suspend operator fun invoke(enable: Boolean){

        val settings = getSettingsUseCase()
        val newSettings = settings.copy(autoChangeProfile = enable)
        setSettingsUseCase(newSettings)

    }

}