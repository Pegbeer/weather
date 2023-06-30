package com.pegbeer.domain.model

data class ForecastDay (
    val maxtempC: Double,
    val maxtempF: Double,
    val mintempC: Double,
    val mintempF: Double,
    val avgtempC: Long,
    val avgtempF: Double,
    val maxwindMph: Double,
    val maxwindKph: Double,
    val totalprecipMm: Double,
    val totalprecipIn: Double,
    val totalsnowCM: Long,
    val avgvisKM: Long,
    val avgvisMiles: Long,
    val avghumidity: Long,
    val dailyWillItRain: Long,
    val dailyChanceOfRain: Long,
    val dailyWillItSnow: Long,
    val dailyChanceOfSnow: Long,
    val weatherCondition: WeatherCondition,
    val uv: Long
)