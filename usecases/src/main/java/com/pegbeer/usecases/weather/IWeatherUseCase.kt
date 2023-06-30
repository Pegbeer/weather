package com.pegbeer.usecases.weather

import com.pegbeer.domain.model.City
import com.pegbeer.domain.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface IGetWeatherUseCase {
    suspend fun getWeather(city: City): Flow<WeatherResponse?>
}