package com.profile.changer.ui.model

import android.app.AlarmManager
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.profile.changer.data.di.components.AppComponent
import com.profile.changer.domain.entities.DeviceProfile
import com.profile.changer.domain.entities.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SettingsScreenModel(private val appComponent: AppComponent): ScreenModel {

    val settings = MutableStateFlow<Settings?>(null)

    val activeProfile = MutableStateFlow<DeviceProfile?>(null)

    init {

        screenModelScope.launch(Dispatchers.IO) {

            update()

        }
    }

    suspend fun update() {

        val active = appComponent.getActiveProfileUseCase()
        val s = appComponent.getSettingsUseCase()

        settings.emit(s)
        activeProfile.emit(active)
    }


    //TODO исправить баг с UI
    private fun runInterval(){

        val interval = (settings.value!!.changeIntervalInHours * 3600 * 1000).toLong()


        appComponent.alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis(),
            interval,
            appComponent.pendingIntent
        )
    }

    fun setAutoChangeEnabled(enabled: Boolean){
        screenModelScope.launch(Dispatchers.IO) {
            appComponent.enableAutoChangeProfilesUseCase(enabled)
            if(enabled)
                runInterval()
            else
                appComponent.alarmManager.cancel(appComponent.pendingIntent)

        }
    }

    fun setUpdateInterval(interval: Int){
        screenModelScope.launch(Dispatchers.IO) {
            appComponent.setTimeIntervalUseCase(interval)
            appComponent.alarmManager.cancel(appComponent.pendingIntent)
            runInterval()
        }
    }

}