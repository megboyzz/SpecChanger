package com.profile.changer.ui.screen

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.profile.changer.data.App
import com.profile.changer.ui.components.AutoChangeSwitcher
import com.profile.changer.ui.components.Profile
import com.profile.changer.ui.components.SettingsScaffold
import com.profile.changer.ui.model.SettingsScreenModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsScreen: Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val appComponent =  ((LocalContext.current as Activity).application as App).appComponent

        val settingsHomeScreen = rememberScreenModel { SettingsScreenModel(appComponent) }

        val settings = settingsHomeScreen.settings.collectAsState()

        val activeProfile by settingsHomeScreen.activeProfile.collectAsState()

        val scope = rememberCoroutineScope()

        SettingsScaffold(onBackPressed = {
            navigator.pop()
            scope.launch(Dispatchers.IO) {
                settingsHomeScreen.update()
            }
        }) {
            Column {
                Profile(activeProfile)
                if(settings.value != null) {
                    AutoChangeSwitcher(
                        enabled = settings.value!!.autoChangeProfile,
                        onSwitch = settingsHomeScreen::setAutoChangeEnabled,
                        interval = settings.value!!.changeIntervalInHours,
                        onIntervalChange = settingsHomeScreen::setUpdateInterval
                    )
                }
            }
        }

    }
}