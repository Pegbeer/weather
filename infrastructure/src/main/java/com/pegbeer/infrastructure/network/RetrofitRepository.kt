package com.pegbeer.infrastructure.network

import com.pegbeer.domain.dao.WeatherDao
import com.pegbeer.domain.model.Location
import com.pegbeer.domain.model.Weather
import com.pegbeer.domain.model.WeatherResponse
import com.pegbeer.domain.repository.IWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RetrofitRepository(private val apiService: IApiService,private val dao:WeatherDao):IWeatherRepository {

    override suspend fun getByLocation(location: Location): WeatherResponse?{
        val str = String.format("%f,%f", location.lat,location.lon)
        val response = apiService.getWeatherByLocation(WEATHER_API_KEY,str)
        if(!response.isSuccessful){
            return null
        }
        return response.body()
    }

    override fun getLocations(query: String): Flow<List<Location>> = flow {
        val response = apiService.getLocation(WEATHER_API_KEY,query)
        if(response.isSuccessful){
            emit(response.body() ?: emptyList())
        }
    }

    override suspend fun saveFavorite(weather: Weather): Long {
        return 0
    }

    companion object{
        const val WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather"
        const val WEATHER_API_KEY = "4162a378b7514207bbe214010231306"
    }
}