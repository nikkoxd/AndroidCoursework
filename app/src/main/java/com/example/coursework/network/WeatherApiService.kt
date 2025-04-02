package com.example.coursework.network

import com.example.coursework.CurrentWeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val API_KEY = "06008a534fe7418c84d123839240108"
private const val BASE_URL = "http://api.weatherapi.com/v1/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface WeatherApiService {
    @GET("current.json?key=$API_KEY&q=London&aqi=no")
    suspend fun getCurrent(): CurrentWeatherResponse
}

object WeatherApi {
    val retrofitService : WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}