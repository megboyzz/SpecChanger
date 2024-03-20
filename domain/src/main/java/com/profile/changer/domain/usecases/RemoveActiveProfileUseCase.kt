package com.profile.changer.domain.usecases

import com.profile.changer.domain.repositories.ProfileRepository
import javax.inject.Inject

class RemoveActiveProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke() = profileRepository.removeActiveProfile()

}