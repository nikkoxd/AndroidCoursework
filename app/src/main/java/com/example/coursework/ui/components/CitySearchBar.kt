package com.example.coursework.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.coursework.R
import com.example.coursework.ui.screens.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitySearchBar(
    viewModel: WeatherViewModel,
    cities: List<String>,
    modifier: Modifier
) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    Box (modifier = modifier) {
        SearchBar(
            query = text,
            onQueryChange = {
                text = it
            },
            onSearch = {
                active = false
                if (it != "") {
                    viewModel.getWeather(it)
                }
            },
            active = active,
            onActiveChange = {
                active = it
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.search_24px),
                    contentDescription = "Search"
                )
            },
            modifier = Modifier.align(Alignment.TopCenter),
        ) {
            Column (Modifier.verticalScroll(rememberScrollState())) {
                cities.forEach { city ->
                    ListItem(
                        headlineContent = { Text(city) },
                        modifier = Modifier
                            .clickable {
                                text = city
                                active = false
                                viewModel.getWeather(city)
                            }
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}
