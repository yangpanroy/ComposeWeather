package com.roy.composeweather

import android.app.Application
import android.content.Context

class MainApp : Application() {

    companion object {
        lateinit var context: Context
        const val APP_NAME = "ComposeWeather"
        const val TOKEN = "h7Am2dn6jKlDgNRZ"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}