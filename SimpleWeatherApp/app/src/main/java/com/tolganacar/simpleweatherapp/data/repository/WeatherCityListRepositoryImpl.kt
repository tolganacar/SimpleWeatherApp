package com.tolganacar.simpleweatherapp.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tolganacar.simpleweatherapp.data.model.weathercitylist.WeatherCityListResponseModel
import com.tolganacar.simpleweatherapp.util.ResourceProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.io.IOException
import javax.inject.Inject

class WeatherCityListRepositoryImpl @Inject constructor(
    private val resourceProvider: ResourceProvider
) : WeatherCityListRepository {

    override suspend fun getCityList(): Flow<WeatherCityListResponseModel> {
        var jsonString: String? = null
        try {
            jsonString = resourceProvider.getAsset("city.list.json")
        } catch (ioException: IOException) {
            println(ioException.message)
        }

        val cityListResponseType = object : TypeToken<WeatherCityListResponseModel>() {}.type
        val cityListResponseModel =
            Gson().fromJson<WeatherCityListResponseModel>(jsonString, cityListResponseType)
        return flowOf(cityListResponseModel)
    }
}