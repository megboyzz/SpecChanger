package com.profile.changer.data.di.components

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import com.profile.changer.data.di.modules.AppModule
import com.profile.changer.data.di.modules.RepositoryModule
import com.profile.changer.data.util.AlarmBroadcastReceiver
import com.profile.changer.domain.usecases.ChangeProfileUseCase
import com.profile.changer.domain.usecases.GetActiveProfileUseCase
import com.profile.changer.domain.usecases.GetAllProfilesUseCase
import com.profile.changer.domain.usecases.RemoveActiveProfileUseCase
import com.profile.changer.domain.usecases.core.settings.GetSettingsUseCase
import com.profile.changer.domain.usecases.core.settings.SetSettingsUseCase
import com.profile.changer.domain.usecases.settings.EnableAutoChangeProfileUseCase
import com.profile.changer.domain.usecases.settings.SetTimeIntervalUseCase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, AppModule::class])
interface AppComponent {

    val setSettingsUseCase: SetSettingsUseCase

    val getSettingsUseCase: GetSettingsUseCase

    val enableAutoChangeProfilesUseCase: EnableAutoChangeProfileUseCase

    val setTimeIntervalUseCase: SetTimeIntervalUseCase

    val changeProfileUseCase: ChangeProfileUseCase

    val getActiveProfileUseCase: GetActiveProfileUseCase

    val getAllProfileUseCase: GetAllProfilesUseCase

    val removeActiveProfileUseCase: RemoveActiveProfileUseCase


    val alarmManager: AlarmManager

    val pendingIntent: PendingIntent

    fun inject(alarmBroadcastReceiver: AlarmBroadcastReceiver)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }


}