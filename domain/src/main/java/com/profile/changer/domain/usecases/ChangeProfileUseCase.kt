package com.profile.changer.domain.usecases

import com.profile.changer.domain.entities.DeviceProfile
import com.profile.changer.domain.repositories.ProfileRepository
import javax.inject.Inject

class ChangeProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke(profile: DeviceProfile? = null) {

        if(profile != null) profileRepository.setActiveProfile(profile)
        else {

            val all = profileRepository.all

            val activeProfile = profileRepository.getActiveProfile()
            if(activeProfile != null) {

                val index = all.indexOf(activeProfile) + 1

                if(index < all.size)
                    profileRepository.setActiveProfile(all[index])
                else
                    profileRepository.setActiveProfile(all.first())

            }else profileRepository.setActiveProfile(all.first())
        }
    }

}