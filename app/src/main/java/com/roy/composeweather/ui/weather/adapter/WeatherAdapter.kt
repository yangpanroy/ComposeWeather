package com.roy.composeweather.ui.weather.adapter

import com.roy.composeweather.logic.model.DailyResponse
import com.roy.composeweather.logic.model.getSky
import com.roy.composeweather.ui.weather.model.BriefForecastItem
import com.roy.composeweather.ui.weather.model.RealtimeLifeIndex
import java.text.SimpleDateFormat

fun getWindDirectionDesc(direction: Double): String {
    return when {
        direction in 0.0..22.5 -> {
            "东风"
        }
        direction in 22.5..67.5 -> {
            "东北风"
        }
        direction in 67.5..112.5 -> {
            "北风"
        }
        direction in 112.5..157.5 -> {
            "西北风"
        }
        direction in 157.5..202.5 -> {
            "西风"
        }
        direction in 202.5..247.5 -> {
            "西南风"
        }
        direction in 247.5..292.5 -> {
            "南风"
        }
        direction in 292.5..337.5 -> {
            "东南风"
        }
        direction <= 360 -> {
            "东风"
        }
        else -> {
            "未知"
        }
    }
}

fun getBriefForecast(daily: DailyResponse.Daily) : List<BriefForecastItem> {
    val formatPattern = SimpleDateFormat("yyyy-MM-dd")
    return daily.temperature.mapIndexed { index, temperatureItem ->
        val skyconItem = daily.skycon[index]
        BriefForecastItem(
            dateStr = formatPattern.format(skyconItem.date),
            skycon = getSky(skyconItem.value).info,
            skyconIcon = getSky(skyconItem.value).icon,
            temperatureStr = "${temperatureItem.min.toInt()}~${temperatureItem.max.toInt()}℃"
        )
    }
}

fun getLifeIndex(daily: DailyResponse.Daily) : RealtimeLifeIndex {
    val lifeIndex = daily.life_index
    return RealtimeLifeIndex(
        coldRisk = lifeIndex.coldRisk[0].desc,
        carWashing = lifeIndex.carWashing[0].desc,
        ultraviolet = lifeIndex.ultraviolet[0].desc,
        dressing = lifeIndex.dressing[0].desc
    )
}