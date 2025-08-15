package com.example.weatherwearosapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherwearosapp.presentation.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val weather = viewModel.state.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF81D4FA), Color(0xFF0288D1))
                )
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        if (weather == null) {
            Text(
                text = "Cargando clima...",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 24.dp)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = "📍 ${weather.name}",
                        color = Color.White,
                        fontSize = 22.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                item { WeatherCard("🌡 Temperatura", "${weather.main.temp}°C") }
                item { WeatherCard("🤔 Sensación", "${weather.main.feelsLike}°C") }
                item { WeatherCard("💨 Viento", "${weather.wind.speed} m/s") }
                item {
                    WeatherCard(
                        "🌥 Condición",
                        weather.weather.firstOrNull()?.description?.replaceFirstChar { it.uppercase() } ?: "N/A"
                    )
                }
            }
        }
    }
}

@Composable
fun WeatherCard(label: String, value: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xCCFFFFFF)),
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(horizontal = 8.dp)
            .height(70.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = label,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}
