package com.example.coursework.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.coursework.R
import com.example.coursework.models.ForecastResponse
import com.example.coursework.ui.components.CityInfo
import com.example.coursework.ui.components.CurrentInfo
import com.example.coursework.ui.components.ThreeDaysInfo
import com.example.coursework.ui.components.TodayInfo
import com.example.coursework.weatherIconMap

@Composable
fun HomeScreen (weatherUiState: WeatherUiState, modifier: Modifier = Modifier) {
    when (weatherUiState) {
        is WeatherUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is WeatherUiState.Success -> WeatherScreen(weatherUiState.weather, modifier)
        is WeatherUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text("Loading...", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text("Loading failed.", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun WeatherScreen (weather: ForecastResponse, modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(top = 16.dp).padding(horizontal = 12.dp).fillMaxSize()
    ) {
        CityInfo(
            weather = weather,
            modifier = Modifier.fillMaxWidth()
        )
        Icon(
            painter = painterResource(
                weatherIconMap.getOrDefault(
                    weather.current.condition.code,
                    R.drawable.sunny_24px
                )
            ),
            contentDescription = weather.current.condition.text,
            modifier = Modifier.size(64.dp).align(Alignment.CenterHorizontally)
        )
        CurrentInfo(
            weather = weather,
            modifier = Modifier.height(72.dp).fillMaxWidth()
        )
        TodayInfo(
            weather = weather,
            modifier = Modifier.fillMaxWidth()
        )
        ThreeDaysInfo(
            weather = weather,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
