package com.tolganacar.simpleweatherapp.data.repository.weathercitylist

import com.tolganacar.simpleweatherapp.data.model.weathercitylist.WeatherCityListResponseModel
import kotlinx.coroutines.flow.Flow

interface WeatherCityListRepository {
    suspend fun getCityList(): Flow<WeatherCityListResponseModel>
}