package com.example.weatherwearosapp.domain

import com.example.weatherwearosapp.data.WeatherRepository
import com.example.weatherwearosapp.data.model.WeatherResponse

class GetWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(lat: Double, lon: Double, apiKey: String): WeatherResponse {
        return repository.getWeather(lat, lon, apiKey)
    }
}
