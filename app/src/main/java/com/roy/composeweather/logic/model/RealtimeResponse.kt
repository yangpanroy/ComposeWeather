package com.roy.composeweather.logic.model

//data class RealtimeResponse(
//    val status: String,
//    val result: Result
//) {
//    data class Result(
//        val realtime: Realtime
//    )
//
//    data class Realtime(
//        val skycon: String,
//        val temperature: Float,
//        @SerializedName("air_quality")
//        val airQuality: AirQuality
//    )
//
//    data class AirQuality(
//        val aqi: AQI
//    )
//
//    data class AQI(
//        val chn: Float
//    )
//}

data class RealtimeResponse(
    val result: Result,
    val status: String
) {
    data class Result(
        val realtime: Realtime
    )

    data class Realtime(
        val air_quality: AirQuality, // 空气质量相关
        val apparent_temperature: Double, // 体感温度
        val humidity: Double, // 湿度
        val skycon: String, // 天气描述
        val temperature: Double, // 温度
        val visibility: Double, // 能见度
        val wind: Wind // 风力
    )

    data class AirQuality(
        val aqi: Aqi,
        val co: Double,
        val description: Description,
        val no2: Double,
        val o3: Double,
        val pm10: Double,
        val pm25: Double,
        val so2: Double
    )

    data class Wind(
        val direction: Double,
        val speed: Double
    )

    data class Aqi(
        val chn: Double
    )

    data class Description(
        val chn: String
    )

}
