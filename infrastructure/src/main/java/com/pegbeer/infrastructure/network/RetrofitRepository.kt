package com.pegbeer.infrastructure.network

import com.pegbeer.domain.dao.WeatherDao
import com.pegbeer.domain.model.City
import com.pegbeer.domain.model.Weather
import com.pegbeer.domain.model.WeatherResponse
import com.pegbeer.domain.repository.IWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow

class RetrofitRepository(private val apiService: IApiService,private val dao:WeatherDao):IWeatherRepository {


    override suspend fun loadCities() {

    }


    override fun getByLocation(city: City): Flow<WeatherResponse?> = flow {
        val str = String.format("%s,%s",city.name,city.country)
        val response = apiService.getWeatherByLocation(WEATHER_URL,str, WEATHER_API_KEY)
        if(!response.isSuccessful){
            emit(null)
        }
        emit(response.body())
    }

    override fun getCities(query: String): Flow<List<City>> = emptyFlow()

    companion object{
        const val WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather"
        const val WEATHER_API_KEY = "c62c315a10155be73e0394c8187d4f66"
    }
}