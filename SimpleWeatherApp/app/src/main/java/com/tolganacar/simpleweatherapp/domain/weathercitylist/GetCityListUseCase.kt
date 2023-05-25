package com.tolganacar.simpleweatherapp.domain.weathercitylist

import com.tolganacar.simpleweatherapp.base.BaseUseCase
import com.tolganacar.simpleweatherapp.data.model.weathercitylist.WeatherCityListResponseModel
import com.tolganacar.simpleweatherapp.data.repository.WeatherCityListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCityListUseCase @Inject constructor(
    private val repository: WeatherCityListRepository
) : BaseUseCase<Unit, WeatherCityListResponseModel>() {

    override suspend fun execute(params: Unit): Flow<WeatherCityListResponseModel> =
        repository.getCityList()
}