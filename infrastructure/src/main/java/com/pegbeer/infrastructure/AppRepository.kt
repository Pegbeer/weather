package com.pegbeer.infrastructure

import android.content.Context
import com.google.gson.Gson
import com.pegbeer.domain.model.Location
import com.pegbeer.domain.model.Weather
import com.pegbeer.domain.model.WeatherResponse
import com.pegbeer.domain.repository.IWeatherRepository
import com.pegbeer.infrastructure.database.RoomRepository
import com.pegbeer.infrastructure.network.RetrofitRepository
import kotlinx.coroutines.flow.Flow

class AppRepository(
    private val context:Context,
    private val retrofitRepository: RetrofitRepository,
    private val roomRepository: RoomRepository
) : IWeatherRepository{

    val gson = Gson()


    override suspend fun getByLocation(location: Location): WeatherResponse?{
        return retrofitRepository.getByLocation(location)
    }

    override fun getLocations(query: String): Flow<List<Location>> {
        return retrofitRepository.getLocations(query)
    }

    override suspend fun saveFavorite(weather: Weather): Long {
        return roomRepository.saveFavorite(weather)
    }

}