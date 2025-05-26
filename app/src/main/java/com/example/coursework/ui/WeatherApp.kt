package com.example.coursework.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coursework.ui.components.CitySearchBar
import com.example.coursework.ui.screens.HomeScreen
import com.example.coursework.ui.screens.WeatherViewModel

@Composable
fun WeatherApp() {
    val cities = listOf(
        "Izhevsk",
        "Moscow",
        "London",
    )

    val weatherViewModel: WeatherViewModel = viewModel(factory = WeatherViewModel.Factory)

    Scaffold(
        topBar = {
            CitySearchBar(weatherViewModel, cities, modifier = Modifier.fillMaxWidth())
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        HomeScreen(weatherViewModel.weatherUiState, modifier = Modifier.padding(innerPadding))
    }
}