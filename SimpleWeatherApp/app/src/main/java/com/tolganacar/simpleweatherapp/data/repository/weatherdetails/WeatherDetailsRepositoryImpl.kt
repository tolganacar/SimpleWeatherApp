package com.tolganacar.simpleweatherapp.data.repository.weatherdetails

import com.tolganacar.simpleweatherapp.data.model.weathercitylist.WeatherCityRequest
import com.tolganacar.simpleweatherapp.data.model.weatherdetails.WeatherDetailsResponseModel
import com.tolganacar.simpleweatherapp.data.service.WeatherDetailsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.lang.Exception
import javax.inject.Inject

class WeatherDetailsRepositoryImpl @Inject constructor(
    private val service: WeatherDetailsService
) : WeatherDetailsRepository {

    override suspend fun getWeatherDetails(req: WeatherCityRequest): Flow<WeatherDetailsResponseModel> {
        return try {
            flowOf(service.getWeatherDetails(req.cityID))
        } catch (e: Exception) {
            throw e
        }
    }

}