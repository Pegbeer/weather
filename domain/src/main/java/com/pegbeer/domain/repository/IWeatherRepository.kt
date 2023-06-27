package com.pegbeer.domain.repository

import com.pegbeer.domain.model.City
import com.pegbeer.domain.model.Weather
import com.pegbeer.domain.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface IWeatherRepository {
    suspend fun loadCities()
    fun getByLocation(city: City):Flow<WeatherResponse?>
    fun getCities(query:String):Flow<List<City>>
}