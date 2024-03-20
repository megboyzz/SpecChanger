package com.profile.changer.domain.usecases

import com.profile.changer.domain.repositories.ProfileRepository
import javax.inject.Inject

class GetActiveProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke() = profileRepository.getActiveProfile()

}