package com.profile.changer.domain.usecases

import com.profile.changer.domain.repositories.ProfileRepository
import javax.inject.Inject

class GetAllProfilesUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    operator fun invoke() = profileRepository.all

}