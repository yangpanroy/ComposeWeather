package com.roy.composeweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork {

    // 查询地区的动态代理对象
    private val placeService = ServiceCreator.create<PlaceService>()

    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    // 查询天气的动态代理对象
    private val weatherService = ServiceCreator.create<WeatherService>()

    suspend fun getDailyWeather(lng: String, lat: String) = weatherService.getDailyWeather(lng, lat).await()
    suspend fun getRealtimeWeather(lng: String, lat: String) = weatherService.getRealtimeWeather(lng, lat).await()

    /**
     * 为 Retrofit 的 Call<T> 对象添加扩展函数 await()
     * 此处使用协程来处理返回值，如果返回值正常则调用 resume 返回 body，否则抛出异常
     */
    private suspend fun <T> Call<T>.await() : T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("Response中返回的Body为空"))
                }

            })
        }
    }

}