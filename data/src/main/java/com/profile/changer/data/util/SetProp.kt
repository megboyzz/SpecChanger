package com.profile.changer.data.util

import android.annotation.SuppressLint
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader


class SetProp {

    @SuppressLint("PrivateApi")
    operator fun invoke(key: String, value: String = ""){
        val clazz = Class.forName("android.os.SystemProperties")
        val method = clazz.getMethod("set", String::class.java, String::class.java)
        method.invoke(null, "persist.${key}", value)
    }

}