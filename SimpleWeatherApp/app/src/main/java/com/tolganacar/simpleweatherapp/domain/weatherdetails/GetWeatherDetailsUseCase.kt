package com.tolganacar.simpleweatherapp.domain.weatherdetails

import com.tolganacar.simpleweatherapp.base.BaseUseCase
import com.tolganacar.simpleweatherapp.data.model.weathercitylist.WeatherCityRequest
import com.tolganacar.simpleweatherapp.data.model.weatherdetails.getFeelsLikeText
import com.tolganacar.simpleweatherapp.data.model.weatherdetails.getGustText
import com.tolganacar.simpleweatherapp.data.model.weatherdetails.getHumidityText
import com.tolganacar.simpleweatherapp.data.model.weatherdetails.getLatitudeText
import com.tolganacar.simpleweatherapp.data.model.weatherdetails.getLongitudeText
import com.tolganacar.simpleweatherapp.data.model.weatherdetails.getMaxTemperatureText
import com.tolganacar.simpleweatherapp.data.model.weatherdetails.getMinTemperatureText
import com.tolganacar.simpleweatherapp.data.model.weatherdetails.getName
import com.tolganacar.simpleweatherapp.data.model.weatherdetails.getSeaLevel
import com.tolganacar.simpleweatherapp.data.model.weatherdetails.getSpeedText
import com.tolganacar.simpleweatherapp.data.model.weatherdetails.getTemperatureText
import com.tolganacar.simpleweatherapp.data.model.weatherdetails.getWeatherStatus
import com.tolganacar.simpleweatherapp.data.repository.weatherdetails.WeatherDetailsRepository
import com.tolganacar.simpleweatherapp.view.weatherdetails.WeatherDetailsUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetWeatherDetailsUseCase @Inject constructor(
    private val repo: WeatherDetailsRepository
) : BaseUseCase<GetWeatherDetailsUseCase.Params, WeatherDetailsUIModel>() {

    data class Params(
        val cityID: Int
    )

    override suspend fun execute(params: Params): Flow<WeatherDetailsUIModel> =
        repo.getWeatherDetails(WeatherCityRequest(params.cityID)).map {
            with(it) {
                WeatherDetailsUIModel(
                    cityID = id ?: params.cityID,
                    name = getName(),
                    temperature = getTemperatureText(),
                    feelsLike = getFeelsLikeText(),
                    maxTemperature = getMaxTemperatureText(),
                    minTemperature = getMinTemperatureText(),
                    humidity = getHumidityText(),
                    speed = getSpeedText(),
                    gust = getGustText(),
                    seaLevel = getSeaLevel(),
                    latitude = getLatitudeText(),
                    longitude = getLongitudeText(),
                    weatherStatus = getWeatherStatus()
                )
            }
        }
}