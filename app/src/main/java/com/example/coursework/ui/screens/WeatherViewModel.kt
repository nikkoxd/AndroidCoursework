package com.example.coursework.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.coursework.WeatherApplication
import com.example.coursework.data.WeatherRepository
import com.example.coursework.models.ForecastResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface WeatherUiState {
    data class Success(val weather: ForecastResponse) : WeatherUiState
    data object Error : WeatherUiState
    data object Loading : WeatherUiState
}

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    var weatherUiState: WeatherUiState by mutableStateOf(WeatherUiState.Loading)
        private set

    init {
        getWeather("Izhevsk")
    }

    fun getWeather(city: String) {
        viewModelScope.launch {
            weatherUiState = WeatherUiState.Loading
            weatherUiState = try {
                WeatherUiState.Success(weatherRepository.getForecast(city))
            } catch (e: IOException) {
                WeatherUiState.Error
            } catch (e: HttpException) {
                WeatherUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as WeatherApplication)
                val weatherRepository = application.container.weatherRepository
                WeatherViewModel(weatherRepository = weatherRepository)
            }
        }
    }
}