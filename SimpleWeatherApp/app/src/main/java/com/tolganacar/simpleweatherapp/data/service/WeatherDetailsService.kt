package com.tolganacar.simpleweatherapp.data.service

import com.tolganacar.simpleweatherapp.data.model.weatherdetails.WeatherDetailsResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherDetailsService {

    @GET(CURRENT_WEATHER)
    suspend fun getWeatherDetails(@Query(CITY_ID) cityID: Int): WeatherDetailsResponseModel

    companion object {
        const val CITY_ID = "id"
        const val CURRENT_WEATHER = "data/2.5/weather?&units=metric"
    }
}