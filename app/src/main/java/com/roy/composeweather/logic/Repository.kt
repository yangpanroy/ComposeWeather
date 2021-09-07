package com.roy.composeweather.logic

import com.roy.composeweather.common.LogUtils
import com.roy.composeweather.logic.dao.PlaceDao
import com.roy.composeweather.logic.model.Place
import com.roy.composeweather.logic.model.Weather
import com.roy.composeweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

object Repository {

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavedPlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

    suspend fun searchPlaces(query: String) = withContext(Dispatchers.IO) {
        // 执行网络请求获得返回
        val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
        // 判断返回情况
        return@withContext if (placeResponse.status == "ok") {
            placeResponse.places
        } else { // 返回情况出现异常
            LogUtils.e("返回值状态为${placeResponse.status}")
            null
        }
    }

    suspend fun refreshWeather(lng: String, lat: String) = withContext(Dispatchers.IO) {
        // 定义两个异步任务分别调用网络请求
        val deferredRealtime = async {
            SunnyWeatherNetwork.getRealtimeWeather(lng, lat)
        }
        val deferredDaily = async {
            SunnyWeatherNetwork.getDailyWeather(lng, lat)
        }
        // 使用 await 方法阻塞异步任务直至两个任务均执行完毕获得返回
        val realtimeResponse = deferredRealtime.await()
        val dailyResponse = deferredDaily.await()
        // 判断返回情况
        if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
            // 将数据拼凑为一个整体的数据类型进行返回
            return@withContext Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
        } else { // 返回情况出现异常
            LogUtils.e("realtime response status is ${realtimeResponse.status} daily response status is ${dailyResponse.status}")
            return@withContext null
        }
    }

    suspend fun getRealtimeWeather(lng: String, lat: String) = withContext(Dispatchers.IO) {
        val realtimeResponse = SunnyWeatherNetwork.getRealtimeWeather(lng, lat)
        if (realtimeResponse.status == "ok") {
            return@withContext realtimeResponse.result.realtime
        } else {
            LogUtils.e("realtime response status is ${realtimeResponse.status}")
            return@withContext null
        }
    }

    suspend fun getDailyWeather(lng: String, lat: String) = withContext(Dispatchers.IO) {
        val dailyResponse = SunnyWeatherNetwork.getDailyWeather(lng, lat)
        if (dailyResponse.status == "ok") {
            return@withContext dailyResponse.result.daily
        } else {
            LogUtils.e("daily response status is ${dailyResponse.status}")
            return@withContext null
        }
    }

}