package com.example.coursework.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.coursework.R
import com.example.coursework.models.ForecastResponse
import com.example.coursework.weatherIconMap

fun getHourString(hour: Int): String {
    return if (hour - 12 > 0) {
        "${hour - 12} PM"
    } else {
        "${if (hour == 0) 12 else hour} ${if (hour == 12) "PM" else "AM"}"
    }
}

@Composable
fun TodayInfo(weather: ForecastResponse, modifier: Modifier) {
    if (weather.forecast.forecastday.isNotEmpty()) {
        Column(modifier = modifier) {
            Text(
                text = "Hourly forecast",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Card {
                LazyRow(modifier = Modifier.padding(8.dp)) {
                    items(24) { hour ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        ) {
                            Text(
                                text = getHourString(hour)
                            )
                            Icon(
                                painter = painterResource(
                                    weatherIconMap.getOrDefault(
                                        weather.forecast.forecastday[0].hour[hour].condition.code,
                                        R.drawable.sunny_24px
                                    )
                                ),
                                contentDescription = weather.current.condition.text,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                            Text(
                                text = "${weather.forecast.forecastday[0].hour[hour].temp_c}°",
                            )
                        }
                    }
                }
            }
        }
    }
}
