package com.roy.composeweather.logic.model

import java.util.*

//data class DailyResponse(
//    val status: String,
//    val result: Result
//) {
//    data class Result(
//        val daily: Daily
//    )
//
//    data class Daily(
//        val temperature: List<Temperature>,
//        val skycon: List<Skycon>,
//        @SerializedName("life_index")
//        val lifeIndex: LifeIndex
//    )
//
//    data class Temperature(
//        val max: Float,
//        val min: Float
//    )
//
//    data class Skycon(
//        val value: String,
//        val date: Date
//    )
//
//    data class LifeIndex(
//        val coldRisk: List<LifeDesc>,
//        val carWashing: List<LifeDesc>,
//        val ultraviolet: List<LifeDesc>,
//        val dressing: List<LifeDesc>
//    )
//
//    data class LifeDesc(
//        val desc: String
//    )
//}

data class DailyResponse(
    val result: Result,
    val status: String
) {
    data class Result(
        val daily: Daily
    )

    data class Daily(
        val air_quality: AirQuality,
        val astro: List<Astro>,
        val cloudrate: List<IndexDetail>,
        val dswrf: List<IndexDetail>,
        val humidity: List<IndexDetail>,
        val life_index: LifeIndex,
        val precipitation: List<IndexDetail>,
        val pressure: List<IndexDetail>,
        val skycon: List<Skycon>,
        val temperature: List<IndexDetail>,
        val visibility: List<IndexDetail>,
        val wind: List<Wind>
    )

    data class AirQuality(
        val aqi: List<Aqi>,
        val pm25: List<IndexDetail>
    )

    data class Astro(
        val date: Date,
        val sunrise: Time,
        val sunset: Time
    )

    data class IndexDetail(
        val avg: Double,
        val date: Date,
        val max: Double,
        val min: Double
    )

    data class LifeIndex(
        val carWashing: List<Desc>,
        val coldRisk: List<Desc>,
        val comfort: List<Desc>,
        val dressing: List<Desc>,
        val ultraviolet: List<Desc>
    )

    data class Skycon(
        val date: Date,
        val value: String
    )

    data class Wind(
        val avg: WindDetail,
        val date: Date,
        val max: WindDetail,
        val min: WindDetail
    )

    data class Aqi(
        val avg: ChnIndex,
        val date: Date,
        val max: ChnIndex,
        val min: ChnIndex
    )

    data class ChnIndex(
        val chn: Double
    )

    data class Time(
        val time: String
    )

    data class Desc(
        val desc: String
    )

    data class WindDetail(
        val direction: Double,
        val speed: Double
    )
}
