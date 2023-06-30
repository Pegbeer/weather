package com.pegbeer.infrastructure.database

import com.pegbeer.domain.dao.WeatherDao
import com.pegbeer.domain.model.Location
import com.pegbeer.domain.model.Weather
import com.pegbeer.domain.model.WeatherResponse
import com.pegbeer.domain.repository.IWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RoomRepository(
    private val dao:WeatherDao
) : IWeatherRepository {
    override suspend fun getByLocation(location: Location): WeatherResponse? {
        return null
    }

    override fun getLocations(query:String):Flow<List<Location>> = flow {
        emit(emptyList())
    }

    override suspend fun saveFavorite(weather: Weather): Long {
        return dao.insert(weather)
    }

}