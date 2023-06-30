package com.pegbeer.usecases.weather

import com.pegbeer.domain.model.Location
import com.pegbeer.domain.model.WeatherResponse

interface IWeatherUseCase {
    suspend fun getWeather(location: Location): WeatherResponse?
}