package com.tolganacar.simpleweatherapp.data.repository

import com.tolganacar.simpleweatherapp.data.model.weathercitylist.WeatherCityListResponseModel
import kotlinx.coroutines.flow.Flow

interface WeatherCityListRepository {
    suspend fun getCityList(): Flow<WeatherCityListResponseModel>
}