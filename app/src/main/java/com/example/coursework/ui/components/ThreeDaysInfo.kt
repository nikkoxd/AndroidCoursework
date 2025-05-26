package com.example.coursework.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.coursework.R
import com.example.coursework.models.ForecastResponse
import com.example.coursework.weatherIconMap
import java.time.Instant
import java.util.Calendar
import java.util.Date

fun getDayOfWeekName(dayOfWeek: Int) : String {
    return when (dayOfWeek) {
        1 -> "Sunday"
        2 -> "Monday"
        3 -> "Tuesday"
        4 -> "Wednesday"
        5 -> "Thursday"
        6 -> "Friday"
        7 -> "Saturday"
        else -> "Monday"
    }
}

@Composable
fun ThreeDaysInfo(weather: ForecastResponse, modifier: Modifier) {
    if (weather.forecast.forecastday.isNotEmpty()) {
        val calendar = Calendar.getInstance()
        Column (modifier = modifier) {
            Text(
                text = "3-days forecast",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            LazyColumn {
                items (weather.forecast.forecastday.size) { day ->
                    Card (modifier = Modifier.padding(vertical = 4.dp)) {
                        val timestamp =
                            Instant.ofEpochSecond(weather.forecast.forecastday[day].date_epoch)
                        val date = Date.from(timestamp)
                        calendar.time = date
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.padding(12.dp).fillMaxWidth()
                        ) {
                            Text(
                                text = getDayOfWeekName(calendar.get(Calendar.DAY_OF_WEEK)),
                                modifier = Modifier.weight(2f)
                            )
                            Icon(
                                painter = painterResource(
                                    weatherIconMap.getOrDefault(
                                        weather.forecast.forecastday[day].day.condition.code,
                                        R.drawable.sunny_24px
                                    )
                                ),
                                contentDescription = weather.current.condition.text,
                                modifier = Modifier.weight(1f)
                            )
                            Row (modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "${weather.forecast.forecastday[day].day.maxtemp_c}°",
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "/${weather.forecast.forecastday[day].day.mintemp_c}°",
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}