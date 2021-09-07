package com.roy.composeweather.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roy.composeweather.common.showToast
import com.roy.composeweather.logic.Repository
import com.roy.composeweather.logic.model.Place
import com.roy.composeweather.logic.model.getSky
import com.roy.composeweather.ui.weather.adapter.getBriefForecast
import com.roy.composeweather.ui.weather.adapter.getLifeIndex
import com.roy.composeweather.ui.weather.model.BriefForecastItem
import com.roy.composeweather.ui.weather.model.RealtimeLifeIndex
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
//    private val locationLiveData = MutableLiveData<Location>()
//    var lng = ""
//    var lat = ""
//    var placeName = ""
//    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
//        Repository.refreshWeather(location.lng, location.lat)
//    }

//    fun refreshWeather(lng: String, lat: String) {
//        locationLiveData.value = Location(lng, lat)
//    }

    private val _place = MutableLiveData("")
    val place = _place as LiveData<String>

    private val _temperature = MutableLiveData<String>()
    val temperature = _temperature as LiveData<String>

    private val _skycon = MutableLiveData<String>()
    val skycon = _skycon as LiveData<String>

    private val _skyconBackground = MutableLiveData<Int>()
    val skyconBackground = _skyconBackground as LiveData<Int>

    private val _aqi = MutableLiveData<String>()
    val aqi = _aqi as LiveData<String>

    private val _briefForecast = MutableLiveData<List<BriefForecastItem>>(emptyList())
    val briefForecast = _briefForecast as LiveData<List<BriefForecastItem>>

    private val _realtimeLifeIndex = MutableLiveData<RealtimeLifeIndex>()
    val realtimeLifeIndex = _realtimeLifeIndex as LiveData<RealtimeLifeIndex>

    fun setPlace(place: Place) {
        val lng = place.location.lng
        val lat = place.location.lat
        val placeName = place.name
//        _place.value = placeName
        _place.postValue(placeName)
        refreshWeather(lng, lat)
    }

    private fun refreshWeather(lng: String, lat: String) {
        getRealtimeWeather(lng, lat)
        getDailyWeather(lng, lat)
    }

    private fun getRealtimeWeather(lng: String, lat: String) {
        viewModelScope.launch {
            val realtime = Repository.getRealtimeWeather(lng, lat)
            if (realtime == null) {
                "获取到的实时天气数据为空".showToast()
                return@launch
            } else {
                val sky = getSky(realtime.skycon)
                _temperature.value = realtime.temperature.toString()
                _skycon.value = sky.info
                _skyconBackground.value = sky.bg
                _aqi.value = realtime.air_quality.aqi.chn.toString()
            }
        }
    }

    private fun getDailyWeather(lng: String, lat: String) {
        viewModelScope.launch {
            val daily = Repository.getDailyWeather(lng, lat)
            if (daily == null) {
                "获取到的每日天气数据为空".showToast()
                return@launch
            } else {
                val remoteBriefForecast = getBriefForecast(daily)
                _briefForecast.value = remoteBriefForecast
                val remoteLifeIndex = getLifeIndex(daily)
                _realtimeLifeIndex.value = remoteLifeIndex
            }
        }
    }

}