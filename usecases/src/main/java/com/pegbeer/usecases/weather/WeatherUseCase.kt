package com.pegbeer.usecases.weather

import com.pegbeer.domain.model.Location
import com.pegbeer.domain.model.WeatherResponse
import com.pegbeer.domain.repository.IWeatherRepository

class WeatherUseCase(private val repository: IWeatherRepository): IWeatherUseCase {

    override suspend fun getWeather(location: Location): WeatherResponse? {
        return repository.getByLocation(location)
    }
}