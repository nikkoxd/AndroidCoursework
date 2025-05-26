package com.example.coursework.data

import com.example.coursework.models.ForecastResponse
import com.example.coursework.network.WeatherApiService

interface WeatherRepository {
    suspend fun getForecast(city: String): ForecastResponse
}

class NetworkWeatherRepository(
    private val weatherApiService: WeatherApiService
): WeatherRepository {
    override suspend fun getForecast(city: String): ForecastResponse = weatherApiService.getForecast(city)
}