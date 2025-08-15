package com.example.weatherwearosapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherwearosapp.data.model.WeatherResponse
import com.example.weatherwearosapp.domain.GetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(private val getWeatherUseCase: GetWeatherUseCase) : ViewModel() {
    private val _state = MutableStateFlow<WeatherResponse?>(null)
    val state: StateFlow<WeatherResponse?> = _state

    fun loadWeather(lat: Double, lon: Double, apiKey: String) {
        viewModelScope.launch {
            try {
                val data = getWeatherUseCase(lat, lon, apiKey)
                _state.value = data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
