package com.profile.changer.domain.usecases.settings

import com.profile.changer.domain.usecases.core.settings.GetSettingsUseCase
import com.profile.changer.domain.usecases.core.settings.SetSettingsUseCase
import javax.inject.Inject

class SetTimeIntervalUseCase @Inject constructor(
    private val setSettingsUseCase: SetSettingsUseCase,
    private val getSettingsUseCase: GetSettingsUseCase
) {

    suspend operator fun invoke(intervalInHours: Int){

        val newSettings = getSettingsUseCase().copy(changeIntervalInHours = intervalInHours)
        setSettingsUseCase(newSettings)

    }

}