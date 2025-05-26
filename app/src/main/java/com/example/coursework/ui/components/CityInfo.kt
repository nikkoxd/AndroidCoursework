package com.example.coursework.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.coursework.models.ForecastResponse

@Composable
fun CityInfo(weather: ForecastResponse, modifier: Modifier) {
    Column (modifier = modifier) {
        Text(
            text = weather.location.name,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
        Text(
            text = weather.current.condition.text,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
    }
}
