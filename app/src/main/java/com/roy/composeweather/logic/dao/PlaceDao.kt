package com.roy.composeweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.roy.composeweather.MainApp
import com.roy.composeweather.logic.model.Place

object PlaceDao {

    fun savePlace(place: Place) {
        sharedPreferences().edit(commit = true) {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace() : Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() = MainApp.context.getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)

}