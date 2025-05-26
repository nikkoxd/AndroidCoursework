package com.example.coursework.data

import com.example.coursework.network.WeatherApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val weatherRepository: WeatherRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "http://api.weatherapi.com/v1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }

    override val weatherRepository: WeatherRepository by lazy {
        NetworkWeatherRepository(retrofitService)
    }
}