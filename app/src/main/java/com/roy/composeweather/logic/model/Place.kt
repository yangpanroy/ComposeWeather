package com.roy.composeweather.logic.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlaceResponse(
    val status: String, // 状态
    val places: List<Place> // 地区列表
)

data class Place(
    val name: String, // 名称
    val location: Location, // 位置（经纬度）
    @SerializedName("formatted_address")
    val address: String // 地址
)

data class Location(
    val lng: String, // 经度
    val lat: String // 纬度
)