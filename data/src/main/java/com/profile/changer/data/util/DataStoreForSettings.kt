package com.profile.changer.data.util

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.profile.changer.domain.entities.DeviceProfile

val Context.settingsDataStore by preferencesDataStore(name = "settings")