package com.pegbeer.domain.model

data class WeatherResponse(
    val current:Weather,
    val forecast:ForecastPayload
)
