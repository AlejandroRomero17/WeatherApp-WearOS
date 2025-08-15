package com.example.weatherwearosapp.data.model
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val name: String,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind
)

data class Main(
    val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double
)

data class Weather(
    val description: String
)

data class Wind(
    val speed: Double
)
