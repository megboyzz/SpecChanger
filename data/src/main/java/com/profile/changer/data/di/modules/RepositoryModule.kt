package com.profile.changer.data.di.modules

import com.profile.changer.data.repository.ProfileRepositoryImpl
import com.profile.changer.data.repository.SettingsRepositoryImpl
import com.profile.changer.domain.repositories.ProfileRepository
import com.profile.changer.domain.repositories.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun bindSettingsRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository {
        return settingsRepositoryImpl
    }

    @Provides
    fun bindProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository{
        return profileRepositoryImpl
    }

}