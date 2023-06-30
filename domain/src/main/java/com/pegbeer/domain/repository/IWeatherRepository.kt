package com.pegbeer.domain.repository

import com.pegbeer.domain.model.Location
import com.pegbeer.domain.model.Weather
import com.pegbeer.domain.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface IWeatherRepository {
    suspend fun getByLocation(location: Location):WeatherResponse?
    fun getLocations(query:String):Flow<List<Location>>

    suspend fun saveFavorite(weather:Weather):Long
}