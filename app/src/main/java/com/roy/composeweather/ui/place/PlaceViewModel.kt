package com.roy.composeweather.ui.place

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roy.composeweather.common.showToast
import com.roy.composeweather.logic.Repository
import com.roy.composeweather.logic.model.Place
import kotlinx.coroutines.launch

class PlaceViewModel : ViewModel() {

    private val _placeList = MutableLiveData(listOf<Place>())
    val placeList = _placeList as LiveData<List<Place>>

    fun searchPlaces(query: String) {
        if (query.isBlank()) return
        viewModelScope.launch {
            val remotePlaces = Repository.searchPlaces(query)
            val updateList = if (remotePlaces.isNullOrEmpty()) {
                "查询结果为空".showToast()
                emptyList()
            } else {
                remotePlaces
            }
            _placeList.value = updateList
        }
    }

    fun savePlace(place: Place) = Repository.savePlace(place)

    fun getSavedPlace() = Repository.getSavedPlace()

    fun isPlaceSaved() = Repository.isPlaceSaved()

}