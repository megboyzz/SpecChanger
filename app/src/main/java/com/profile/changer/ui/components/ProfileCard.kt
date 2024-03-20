package com.profile.changer.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.profile.changer.R
import com.profile.changer.domain.entities.DeviceProfile
import com.profile.changer.ui.asPainter
import com.profile.changer.ui.theme.main

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCard(
    deviceProfile: DeviceProfile,
    onProfileChangeButtonClick: (DeviceProfile) -> Unit,
    buttonLabel: String = "Сменить профиль"
) {

    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, main),
        modifier = Modifier
            .defaultMinSize(400.dp, 60.dp),
        onClick = { 
            expanded =! expanded }
    ) {
        Column(verticalArrangement = Arrangement.Center) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(20.dp, 20.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = R.drawable.phone.asPainter(),
                    contentDescription = null,
                )
                Text(text = deviceProfile.name)
                Image(
                    imageVector = if(expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = null
                )
            }
        }
        AnimatedVisibility(visible = expanded) {
            
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        top = 0.dp,
                        bottom = 20.dp,
                        end = 20.dp
                    )
            ) {
                Divider(color = Color.Black)
                
                Column(
                    modifier = Modifier
                        .height(300.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    deviceProfile.properties.forEach { (key, value) ->
                        Text(text = "$key = $value")
                    }
                }
                
                Button(
                    onClick = {
                        expanded =! expanded
                        onProfileChangeButtonClick(deviceProfile)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = buttonLabel)
                }
            }

        }
    }

}

@Composable
fun ActiveProfileCard(
    activeDeviceProfile: DeviceProfile?,
    onProfileReset: (DeviceProfile) -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if(activeDeviceProfile != null) {
            Text(
                text = "Активный профиль"
            )
            ProfileCard(
                deviceProfile = activeDeviceProfile,
                onProfileChangeButtonClick = onProfileReset,
                buttonLabel = "Вернуть исходный профиль"
            )
        }else{
            Text(
                text = "Нет активных профилей",
                //Modifier.padding(bottom = 20.dp)
            )
        }
        Divider(color = Color.Black)
    }

}


@Preview
@Composable
fun ProfileCardPreview() {

    val deviceProfile = DeviceProfile(
        fileName = "",
        name = "Xiaomi A2 Lite",
        properties = mapOf(
            "ro.build.display.id" to "PKQ1.180917.001.V10.0.1.0.PDLMIFJ",
            "ro.build.id" to "PKQ1.180917.001",
            "ro.build.type" to "user",
            "ro.build.version.release" to "9",
            "ro.build.version.incremental" to "V10.0.1.0.PDLMIFJ",
            "ro.build.version.release_or_codename" to "",
            "ro.build.version.release_or_preview_display" to "",
            "ro.build.version.base_os" to "",
            "ro.build.version.security_patch" to "2018-11-05",
            "ro.build.version.sdk" to "28",
            "ro.build.version.codename" to "REL",
            "ro.build.tags" to "release-keys",
            "ro.build.user" to "builder",
            "ro.build.fingerprint" to "xiaomi/daisy/daisy_sprout:9/PKQ1.180917.001/V10.0.1.0.PDLMIFJ:user/release-keys",
            "ro.product.device" to "daisy_sprout",
            "ro.product.name" to "daisy",
            "ro.product.board" to "",
            "ro.product.cpu.abilist" to "arm64-v8a,armeabi-v7a,armeabi",
            "ro.product.cpu.abilist32" to "armeabi-v7a,armeabi",
            "ro.product.cpu.abilist64" to "arm64-v8a",
            "ro.product.manufacturer" to "Xiaomi",
            "ro.product.brand" to "xiaomi",
            "ro.product.model" to "Mi A2 lite",
            "ro.soc.manufacturer" to "",
            "ro.soc.model" to "",
            "ro.bootloader" to "",
            "ro.hardware" to "",
            "ro.boot.hardware.sku" to "",
            "ro.boot.product.hardware.sku" to "",
            "ro.boot.qemu" to "0",
            "no.such.thing" to "",
            "ro.boot" to "",
            "ro.build.host" to "mi-server"
        )
        )

    //ProfileCard(deviceProfile = deviceProfile){}


}

