package com.roy.composeweather.ui.weather.model

data class BriefForecastItem(
    val dateStr: String,
    val skycon: String,
    val skyconIcon: Int,
    val temperatureStr: String
)
