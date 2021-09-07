package com.roy.composeweather.logic.model

data class Weather (
    val realtime: RealtimeResponse.Realtime,
    val daily: DailyResponse.Daily
)