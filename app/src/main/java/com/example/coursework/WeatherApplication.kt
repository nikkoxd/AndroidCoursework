package com.example.coursework

import android.app.Application
import com.example.coursework.data.AppContainer
import com.example.coursework.data.DefaultAppContainer

class WeatherApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}