package com.profile.changer.ui.screen

import android.app.Activity
import android.os.Environment
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.profile.changer.data.App
import com.profile.changer.ui.components.ActiveProfileCard

import com.profile.changer.ui.components.MainScaffold
import com.profile.changer.ui.components.ProfileCard
import com.profile.changer.ui.model.HomeScreenModel

class HomeScreen : Screen {

    @Composable
    override fun Content() {

        val context = LocalContext.current

        val appComponent =  ((context as Activity).application as App).appComponent

        val screenModel = rememberScreenModel { HomeScreenModel(appComponent) }

        val navigator = LocalNavigator.currentOrThrow

        val listOfProfiles = screenModel.listOfProfiles.collectAsState()
        val activeProfile = screenModel.activeProfile.collectAsState()

        Log.i("HomeScreen", Environment.getDownloadCacheDirectory().absolutePath)

        MainScaffold(
            onSettingsClick = { navigator.push(SettingsScreen()) }
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                ActiveProfileCard(
                    activeDeviceProfile = activeProfile.value,
                    onProfileReset = {
                        screenModel.removeActiveProfile()
                    }
                )
                listOfProfiles.value.forEach {
                    if(it != activeProfile.value) {
                        ProfileCard(
                            deviceProfile = it,
                            onProfileChangeButtonClick = screenModel::setActiveProfile
                        )
                    }
                }
            }
        }

    }



}
