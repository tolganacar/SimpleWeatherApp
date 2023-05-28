package com.tolganacar.simpleweatherapp.data.repository.weathercitylist

import com.tolganacar.simpleweatherapp.util.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class WeatherCityListRepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherCityListRepository(
        resourceProvider: ResourceProvider
    ): WeatherCityListRepository = WeatherCityListRepositoryImpl(resourceProvider)
}