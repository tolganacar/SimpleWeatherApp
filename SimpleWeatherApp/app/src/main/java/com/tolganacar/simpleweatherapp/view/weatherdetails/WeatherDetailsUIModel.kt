package com.tolganacar.simpleweatherapp.view.weatherdetails

data class WeatherDetailsUIModel(
    val cityID: Int,
    val name: String,
    val temperature: String,
    val feelsLike: String,
    val maxTemperature: String,
    val minTemperature: String,
    val humidity: String,
    val speed: String,
    val gust: String,
    val seaLevel: Int,
    val latitude: String,
    val longitude: String,
    val weatherStatus: String
)

