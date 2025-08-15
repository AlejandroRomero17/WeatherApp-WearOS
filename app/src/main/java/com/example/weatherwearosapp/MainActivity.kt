package com.example.weatherwearosapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.weatherwearosapp.presentation.WeatherViewModel
import com.example.weatherwearosapp.data.remote.ApiClient
import com.example.weatherwearosapp.data.remote.WeatherApiService
import com.example.weatherwearosapp.data.WeatherRepository
import com.example.weatherwearosapp.domain.GetWeatherUseCase
import com.example.weatherwearosapp.ui.screens.WeatherScreen
import com.example.weatherwearosapp.ui.theme.WeatherwearosappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val api = ApiClient.retrofit.create(WeatherApiService::class.java)
        val repository = WeatherRepository(api)
        val getWeatherUseCase = GetWeatherUseCase(repository)
        val weatherViewModel = WeatherViewModel(getWeatherUseCase)

        // Cargar datos al iniciar
        weatherViewModel.loadWeather(
            lat =  20.173888888889,
            lon = -98.055,
            apiKey = "9a4399df47258a47755bf81eb107491f"
        )

        setContent {
            WeatherwearosappTheme {
                WeatherScreen(viewModel = weatherViewModel)
            }
        }
    }
}
