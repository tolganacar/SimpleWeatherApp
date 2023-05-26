package com.tolganacar.simpleweatherapp.data.model.weatherdetails

data class WeatherDetailsResponseModel(
    val coord: Coord? = null,
    val id: Int? = null,
    val main: Main? = null,
    val name: String? = null,
    val weather: List<Weather>? = null,
    val wind: Wind? = null
)

data class Main(
    val feels_like: Double,
    val humidity: Int,
    val sea_level: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)

data class Wind(
    val gust: Double,
    val speed: Double
)

data class Weather(
    val description: String,
)

data class Coord(
    val lat: Double,
    val lon: Double
)

fun WeatherDetailsResponseModel.getName(): String {
    return name ?: "Unknown"
}

fun WeatherDetailsResponseModel.getTemperatureText(): String {
    val temperature = main?.temp ?: 0.0
    val degreeSymbol = "\u00B0"
    val celsius = "C"

    return "Current: $temperature$degreeSymbol$celsius"
}

fun WeatherDetailsResponseModel.getFeelsLikeText(): String {
    val maxTemperature = main?.feels_like ?: 0.0
    val degreeSymbol = "\u00B0"
    val celsius = "C"

    return "Feels Like: $maxTemperature$degreeSymbol$celsius"
}

fun WeatherDetailsResponseModel.getMaxTemperatureText(): String {
    val maxTemperature = main?.temp_max ?: 0.0
    val degreeSymbol = "\u00B0"
    val celsius = "C"

    return "H:$maxTemperature$degreeSymbol$celsius"
}

fun WeatherDetailsResponseModel.getMinTemperatureText(): String {
    val minTemperature = main?.temp_min ?: 0.0
    val degreeSymbol = "\u00B0"
    val celsius = "C"

    return "L:$minTemperature$degreeSymbol$celsius"
}

fun WeatherDetailsResponseModel.getHumidityText(): String {
    val humidity = main?.humidity ?: 0
    val percentage = "%"
    return "$percentage$humidity"
}

fun WeatherDetailsResponseModel.getSpeedText(): String {
    val speed = wind?.speed ?: 0.0
    val kmh = "km/h"
    return "Speed: $speed$kmh"
}

fun WeatherDetailsResponseModel.getGustText(): String {
    val gust = wind?.gust ?: 0.0
    val kmh = "km/h"
    return "Gust: $gust$kmh"
}

fun WeatherDetailsResponseModel.getSeaLevel(): Int {
    return main?.sea_level ?: 0
}

fun WeatherDetailsResponseModel.getLatitudeText(): String {
    val latitude = coord?.lat?.toString()?.substring(0,2) ?: 0.0
    return "Latitude: $latitude"
}

fun WeatherDetailsResponseModel.getLongitudeText(): String {
    val longitude = coord?.lon?.toString()?.substring(0,2) ?: 0.0
    return "Longitude: $longitude"
}

fun WeatherDetailsResponseModel.getWeatherStatus(): String {
    return weather?.firstOrNull()?.description ?: "Unknown"
}

