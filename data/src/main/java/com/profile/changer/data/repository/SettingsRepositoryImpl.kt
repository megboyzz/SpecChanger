package com.profile.changer.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.profile.changer.data.util.settingsDataStore
import com.profile.changer.domain.entities.Settings
import com.profile.changer.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val sharedPrefs: SharedPreferences
): SettingsRepository {


    private val autoChangeProfileKey = "is_auto_change"
    private val changeIntervalInHoursKey = "change_interval"

    override var settings: Settings
        get() = Settings(
            autoChangeProfile = sharedPrefs.getBoolean(autoChangeProfileKey, false),
            changeIntervalInHours = sharedPrefs.getInt(changeIntervalInHoursKey, 1)
        )
        set(value) {
            with(sharedPrefs.edit()){
                putBoolean(autoChangeProfileKey, value.autoChangeProfile)
                putInt(changeIntervalInHoursKey, value.changeIntervalInHours)
                apply()
            }
        }


}