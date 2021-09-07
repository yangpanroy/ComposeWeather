package com.roy.composeweather.logic.network

import com.roy.composeweather.MainApp
import com.roy.composeweather.logic.model.DailyResponse
import com.roy.composeweather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("v2.5/${MainApp.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(
        @Path("lng")
        lng: String,
        @Path("lat")
        lat: String
    ) : Call<RealtimeResponse>

    @GET("v2.5/${MainApp.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(
        @Path("lng")
        lng: String,
        @Path("lat")
        lat: String
    ) : Call<DailyResponse>

}