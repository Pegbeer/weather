package com.pegbeer.domain.model

data class WeatherResponse(
    val weather:List<Weather>,
    val main:Main
)
