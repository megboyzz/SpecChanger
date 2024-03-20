package com.profile.changer.data

import android.app.AlarmManager
import android.app.Application
import com.profile.changer.data.di.components.DaggerAppComponent
import com.profile.changer.data.di.components.AppComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().context(this).build()

        GlobalScope.launch(Dispatchers.IO) {
            val settings = appComponent.getSettingsUseCase()

            if(settings.autoChangeProfile) {
                val interval = (settings.changeIntervalInHours * 3600 * 1000).toLong()
                appComponent.alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis(),
                    interval,
                    appComponent.pendingIntent
                )
            }

        }

    }

}