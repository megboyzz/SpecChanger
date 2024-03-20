package com.profile.changer.ui.components

import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alorma.compose.settings.ui.SettingsList
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.alorma.compose.settings.storage.base.SettingValueState
import com.alorma.compose.settings.storage.base.rememberIntSettingState
import com.alorma.compose.settings.ui.SettingsCheckbox
import com.alorma.compose.settings.ui.SettingsGroup
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.alorma.compose.settings.ui.SettingsSwitch
import com.profile.changer.R
import com.profile.changer.domain.entities.DeviceProfile
import com.profile.changer.ui.asPainter

@Preview
@Composable
fun SettingsContent() {

    val list = listOf(
        "1 час",
        "2 часа",
        "5 часов",
        "10 часов",
        "сутки"
    )
    Column {

        SettingsList(
            title = { Text("Интервал переключения профиля") },
            items = list,
        )
    }
}

@Composable
fun Profile(deviceProfile: DeviceProfile?) {

    if(deviceProfile != null) {
        SettingsMenuLink(
            icon = {
                Image(painter = R.drawable.phone.asPainter(), contentDescription = null)
            },
            title = {
                Text(text = deviceProfile.name)
            }
        ) {}
    }

}

data class IntervalItem(
    val title: String,
    val interval: Int
){
    override fun toString() = title
}

@Composable
fun AutoChangeSwitcher(

    enabled: Boolean,
    onSwitch: (Boolean) -> Unit,

    interval: Int,
    onIntervalChange: (Int) -> Unit

) {

    val list = listOf(
        IntervalItem("1 час", 4),
        IntervalItem("2 часа", 2),
        IntervalItem("5 часов", 5),
        IntervalItem("10 часов", 10),
        IntervalItem("сутки", 24)
    )

    var index = list.indexOf(list.find { it.interval == interval })
    index = if(index == -1) 0 else index

    var enabledInner by remember { mutableStateOf(enabled) }
    SettingsGroup(
        title = {
            Text(text = "Настройки")
        }
    ) {

        SettingsMenuLink(
            title = {Text("Включить авто переключение профиля")},
            onClick = {
                enabledInner =! enabledInner
                onSwitch(enabledInner)
            },
            icon = {
                Switch(checked = enabledInner, onCheckedChange = {
                    enabledInner =! enabledInner
                    onSwitch(enabledInner)
                })
            }
        )

        SettingsList(
            title = { Text("Интервал переключения профиля") },
            items = list.map { it.title },
            enabled = enabledInner,
            onItemSelected = { i, _ ->
                onIntervalChange(list[i].interval)
            },
            state = rememberIntSettingState(index)
        )
    }

}

