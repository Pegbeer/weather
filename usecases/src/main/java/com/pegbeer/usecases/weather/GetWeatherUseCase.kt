package com.pegbeer.usecases.weather

import com.pegbeer.domain.model.City
import com.pegbeer.domain.model.WeatherResponse
import com.pegbeer.domain.repository.IWeatherRepository
import kotlinx.coroutines.flow.Flow

class GetWeatherUseCase(private val repository: IWeatherRepository): IGetWeatherUseCase {

    override suspend fun getWeather(city: City): Flow<WeatherResponse?> {
        return repository.getByLocation(city)
    }
}