package com.tolganacar.simpleweatherapp.data.model.weathercitylist

import com.tolganacar.simpleweatherapp.view.weathercitylist.CityListUIModel
import com.tolganacar.simpleweatherapp.view.weathercitylist.CityUI

class WeatherCityListResponseModel : ArrayList<WeatherCityResponseModel>()

data class WeatherCityResponseModel(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val state: String
)

data class Coord(
    val lat: Double,
    val lon: Double
)

fun WeatherCityListResponseModel.toUIModel() = CityListUIModel(
    weatherCityList = this.map {
        it.toUIModel()
    }
)

fun WeatherCityResponseModel.toUIModel() = CityUI(
    cityID = id,
    cityName = name
)