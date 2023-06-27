package com.pegbeer.infrastructure

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pegbeer.domain.model.City
import com.pegbeer.domain.model.Weather
import com.pegbeer.domain.model.WeatherResponse
import com.pegbeer.domain.repository.IWeatherRepository
import com.pegbeer.infrastructure.database.RoomRepository
import com.pegbeer.infrastructure.network.RetrofitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type

class AppRepository(
    private val context:Context,
    private val retrofitRepository: RetrofitRepository,
    private val roomRepository: RoomRepository
) : IWeatherRepository{

    val gson = Gson()

    override suspend fun loadCities(){
        withContext(Dispatchers.IO){
            val inputStream = context.assets.open("city.list.json")
            val reader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line:String?
            while(reader.readLine().also { line = it } != null){
                stringBuilder.append(line)
            }
            val json = stringBuilder.toString()
            val listType: Type = object : TypeToken<List<City>>(){}.type
            val list = gson.fromJson<List<City>>(json, listType)
            roomRepository.saveCities(list)
        }
    }

    override fun getByLocation(city: City): Flow<WeatherResponse?>{
        return retrofitRepository.getByLocation(city)
    }

    override fun getCities(query: String): Flow<List<City>> {
        return roomRepository.getCities(query)
    }

}