package com.example.coursework.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coursework.R
import com.example.coursework.models.ForecastResponse

@Composable
fun CurrentInfo(weather: ForecastResponse, modifier: Modifier) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Column (
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxHeight().weight(1f)
        ) {
            Icon(
                painter = painterResource(R.drawable.air_24px),
                contentDescription = "Wind speed",
                modifier = Modifier.size(32.dp).align(Alignment.CenterHorizontally)
            )
            Text(
                text = "${weather.current.wind_kph} km/h",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        Column (
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxHeight().weight(2f)
        ) {
            Text(
                text = "${weather.current.temp_c}°",
                fontSize = 36.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Feels like ${weather.current.feelslike_c}°",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        Column (
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxHeight().weight(1f)
        ) {
            Icon(
                painter = painterResource(R.drawable.humidity_percentage_24px),
                contentDescription = "Humidity",
                modifier = Modifier.size(32.dp).align(Alignment.CenterHorizontally)
            )
            Text(
                text = "${weather.current.humidity}%",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
