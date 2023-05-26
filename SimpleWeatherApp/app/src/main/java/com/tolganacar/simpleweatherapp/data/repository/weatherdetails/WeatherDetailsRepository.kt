package com.tolganacar.simpleweatherapp.data.repository.weatherdetails

import com.tolganacar.simpleweatherapp.data.model.weathercitylist.WeatherCityRequest
import com.tolganacar.simpleweatherapp.data.model.weatherdetails.WeatherDetailsResponseModel
import kotlinx.coroutines.flow.Flow

interface WeatherDetailsRepository {

    suspend fun getWeatherDetails(req: WeatherCityRequest): Flow<WeatherDetailsResponseModel>
}