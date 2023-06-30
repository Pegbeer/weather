package com.pegbeer.domain.model

data class Forecast(
    val date: String,
    val dateEpoch: Long,
    val day:ForecastDay
)
