package com.profile.changer.data.di.modules

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.profile.changer.data.util.AlarmBroadcastReceiver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun providesAlarmManager(context: Context): AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager


    @Provides
    @Singleton
    fun providesPendingIntent(context: Context): PendingIntent {

        val intent = Intent(context, AlarmBroadcastReceiver::class.java)

        return PendingIntent.getBroadcast(context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE)

    }

    @Provides
    @Singleton
    fun providesSharedPrefs(context: Context) = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

}