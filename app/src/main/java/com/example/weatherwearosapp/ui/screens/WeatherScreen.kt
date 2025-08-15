package com.example.weatherwearosapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import com.example.weatherwearosapp.presentation.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val weather = viewModel.state.collectAsState().value
    val listState: ScalingLazyListState = rememberScalingLazyListState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFF64B5F6), Color(0xFF0D47A1)),
                    center = Offset(200f, 0f),
                    radius = 400f
                )
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        if (weather == null) {
            Text(
                text = "Cargando clima...",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 16.dp)
            )
        } else {
            val items = listOf(
                WeatherItem("🌡 Temperatura", "${weather.main.temp}°C", Color(0xFF1976D2)),
                WeatherItem("🤔 Sensación", "${weather.main.feelsLike}°C", Color(0xFFFFA000)),
                WeatherItem("💨 Viento", "${weather.wind.speed} m/s", Color(0xFF0288D1)),
                WeatherItem(
                    "🌥 Condición",
                    weather.weather.firstOrNull()?.description?.replaceFirstChar { it.uppercase() } ?: "N/A",
                    Color(0xFF1976D2)
                )
            )

            ScalingLazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = "📍 ${weather.name}",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(vertical = 6.dp)
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }

                items(items) { item ->
                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn(animationSpec = tween(300)) +
                                scaleIn(initialScale = 0.8f, animationSpec = tween(300))
                    ) {
                        WeatherCard(
                            label = item.label,
                            value = item.value,
                            fontSize = 14.sp,
                            boldValue = item.label.contains("Temperatura"),
                            accentColor = item.color
                        )
                    }
                }
            }
        }
    }
}

data class WeatherItem(
    val label: String,
    val value: String,
    val color: Color
)

@Composable
fun WeatherCard(
    label: String,
    value: String,
    fontSize: TextUnit = 14.sp,
    boldValue: Boolean = false,
    accentColor: Color = Color(0xFF0D47A1)
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(60.dp)
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color.Black.copy(alpha = 0.15f),
                spotColor = Color.White.copy(alpha = 0.1f)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Brush.verticalGradient(
                colors = listOf(Color.White, accentColor.copy(alpha = 0.15f))
            ).toColor()
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF424242)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                fontSize = fontSize,
                fontWeight = if (boldValue) FontWeight.Bold else FontWeight.Normal,
                color = accentColor,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

// Extensión para convertir Brush a Color de fallback
fun Brush.toColor(): Color = Color(0xFFFFFFFF)
