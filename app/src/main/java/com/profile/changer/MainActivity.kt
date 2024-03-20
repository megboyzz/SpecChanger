package com.profile.changer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.profile.changer.ui.screen.HomeScreen
import com.profile.changer.ui.theme.ProfileChangerTheme
import kotlinx.coroutines.isActive
import kotlin.coroutines.coroutineContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        assets.open("profiles/build_a920f_merged.json")

        setContent {

            ProfileChangerTheme {
                Navigator(HomeScreen()) { navigator ->
                    SlideTransition(navigator) { screen ->
                        screen.Content()
                    }
                }
            }
        }
    }


}
