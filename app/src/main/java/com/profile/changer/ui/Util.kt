package com.profile.changer.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun Int.asString() = stringResource(this)

@Composable
fun Int.asPainter() = painterResource(this)