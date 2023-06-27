package com.pegbeer.infrastructure.database

import com.pegbeer.domain.dao.WeatherDao
import com.pegbeer.domain.model.City
import com.pegbeer.domain.model.Weather
import com.pegbeer.domain.model.WeatherResponse
import com.pegbeer.domain.repository.IWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RoomRepository(
    private val dao:WeatherDao
) : IWeatherRepository {

    override fun getCities(query:String):Flow<List<City>>{
        return dao.getCity(query)
    }


    suspend fun saveCities(cities:List<City>){
        dao.insertAllCities(cities)
    }

    override suspend fun loadCities() {

    }

    override fun getByLocation(city: City): Flow<WeatherResponse?> = flow {
        emit(null)
    }
}