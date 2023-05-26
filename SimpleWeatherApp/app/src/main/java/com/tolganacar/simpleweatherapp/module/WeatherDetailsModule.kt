package com.tolganacar.simpleweatherapp.module

import com.tolganacar.simpleweatherapp.data.repository.weatherdetails.WeatherDetailsRepository
import com.tolganacar.simpleweatherapp.data.repository.weatherdetails.WeatherDetailsRepositoryImpl
import com.tolganacar.simpleweatherapp.data.service.WeatherDetailsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherDetailsModule {

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): WeatherDetailsService {
        return retrofit.create(WeatherDetailsService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(service: WeatherDetailsService): WeatherDetailsRepository {
        return WeatherDetailsRepositoryImpl(service)
    }

}