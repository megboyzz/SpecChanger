package com.profile.changer.ui.model

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.profile.changer.data.di.components.AppComponent
import com.profile.changer.domain.entities.DeviceProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeScreenModel(private val appComponent: AppComponent): ScreenModel {

    val listOfProfiles = MutableStateFlow<List<DeviceProfile>>(listOf())
    val activeProfile = MutableStateFlow<DeviceProfile?>(null)

    init {
        screenModelScope.launch(Dispatchers.IO) {
            update()
        }
    }

    suspend fun update(){
        activeProfile.emit(appComponent.getActiveProfileUseCase())

        val all = appComponent.getAllProfileUseCase()

        listOfProfiles.emit(all)
    }


    fun addProfile(pathToFile: String){

    }

    fun removeActiveProfile(){
        screenModelScope.launch(Dispatchers.IO) {
            appComponent.removeActiveProfileUseCase()
            update()
        }
    }

    fun setActiveProfile(deviceProfile: DeviceProfile){
        screenModelScope.launch(Dispatchers.IO) {

            appComponent.changeProfileUseCase(deviceProfile)
            update()

        }
    }


}