package com.example.coursework.network

import com.example.coursework.models.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "06008a534fe7418c84d123839240108"

interface WeatherApiService {
    @GET("forecast.json?key=$API_KEY&days=3&&aqi=no&alerts=no")
    suspend fun getForecast(@Query("q") q: String): ForecastResponse
}