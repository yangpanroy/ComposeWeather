package com.roy.composeweather.ui.weather

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.roy.composeweather.ui.weather.components.ForecastPanel
import com.roy.composeweather.ui.weather.components.LifeComfortPanel
import com.roy.composeweather.ui.weather.components.WeatherHeaderPanel

@Composable
fun Weather(
    viewModel: WeatherViewModel,
    onClickIcon: () -> Unit
) {
    val temperature = viewModel.temperature.observeAsState("")
    val skycon = viewModel.skycon.observeAsState("")
    val skyconBackground = viewModel.skyconBackground.observeAsState(-1)
    val aqi = viewModel.aqi.observeAsState("")
    val briefForecast = viewModel.briefForecast.observeAsState(emptyList())
    val realtimeLifeIndex = viewModel.realtimeLifeIndex.observeAsState()
    val place = viewModel.place.observeAsState("")

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState(0))
                .fillMaxSize()
        ) {
            WeatherHeaderPanel(
                place.value,
                temperature.value,
                skycon.value,
                skyconBackground.value,
                aqi.value
            ) { onClickIcon() }
            if (briefForecast.value.isNotEmpty()) {
                ForecastPanel(
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { },
                    briefForecast = briefForecast.value
                )
            }
            if (realtimeLifeIndex.value != null) {
                LifeComfortPanel(
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { },
                    realtimeLifeIndex = realtimeLifeIndex.value!!
                )
            }
        }
    }
}