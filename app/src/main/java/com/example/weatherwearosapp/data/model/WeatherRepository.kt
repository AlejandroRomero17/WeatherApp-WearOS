package com.example.weatherwearosapp.data

import com.example.weatherwearosapp.data.model.WeatherResponse
import com.example.weatherwearosapp.data.remote.WeatherApiService

class WeatherRepository(private val api: WeatherApiService) {
    suspend fun getWeather(lat: Double, lon: Double, apiKey: String): WeatherResponse {
        return api.getWeather(lat, lon, apiKey)
    }
}
