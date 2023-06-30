package com.pegbeer.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class WeatherCondition (
    val text: String,
    val icon: String,
    val code: Long
)