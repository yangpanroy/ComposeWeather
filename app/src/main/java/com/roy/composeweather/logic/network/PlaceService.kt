package com.roy.composeweather.logic.network

import com.roy.composeweather.MainApp
import com.roy.composeweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("v2/place?token=${MainApp.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String) : Call<PlaceResponse>

}