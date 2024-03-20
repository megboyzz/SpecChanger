package com.profile.changer.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.profile.changer.data.util.SetProp
import com.profile.changer.data.util.settingsDataStore
import com.profile.changer.domain.entities.DeviceProfile
import com.profile.changer.domain.entities.DeviceProfile as DomainDeviceProfile
import com.profile.changer.domain.repositories.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import okio.Path.Companion.toPath
import java.io.File
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val context: Context,
): ProfileRepository {

    private val activeProfileName = stringPreferencesKey("activeProfile")

    private val setProp = SetProp()

    private val gson = Gson()

    override val all: List<DomainDeviceProfile>
        get() = getAllProfiles()

    override suspend fun getActiveProfile(): DeviceProfile? {
        return context.settingsDataStore.data.map {
            getDeviceProfileByAssetName(it[activeProfileName] ?: return@map null)
        }.first()
    }

    private fun getAllProfiles(): List<DomainDeviceProfile> {

        val list = context.assets.list("profiles")?: return listOf()

        return list.mapNotNull(::getDeviceProfileByAssetName)

    }

    private fun getDeviceProfileByAssetName(assetName: String): DeviceProfile? {

        if(assetName.isEmpty()) return null

        val reader = context.assets.open("profiles/$assetName").reader()

        val s = kotlin.runCatching {
            gson.fromJson(reader, Map::class.java)
        }

        return if(s.isFailure) gson.fromJson(reader, DomainDeviceProfile::class.java)
        else {

            val map = s.getOrNull() as Map<String, String>? ?: return null

            val manufacturer = map["ro.product.manufacturer"] ?: assetName.replace(".json", "")
            val model = map["ro.product.model"] ?: ""

            DomainDeviceProfile(
                name = "$manufacturer $model",
                fileName = assetName,
                properties = map
            )
        }
    }

    //Устнаовка persist props и флажка активного профиля
    override suspend fun setActiveProfile(profile: DomainDeviceProfile): Boolean {

        removeActiveProfile()
        profile.properties.forEach{ (key, value) -> setProp(key, value) }

        context.settingsDataStore.edit {
            it[activeProfileName] = profile.fileName
        }

        //TODO обработать ошибку
        return true
    }

    //Сброс активного профиля
    override suspend fun removeActiveProfile() {

        getActiveProfile()?.properties?.forEach { (key, _) -> setProp(key) }
        context.settingsDataStore.edit { it[activeProfileName] = "" }

    }
}