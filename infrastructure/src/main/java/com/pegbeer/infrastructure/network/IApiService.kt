package com.pegbeer.infrastructure.network

import com.pegbeer.domain.model.Location
import com.pegbeer.domain.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface IApiService {

    @GET("search.json")
    suspend fun getLocation(@Query("key")key:String,@Query("q")query:String):Response<List<Location>>

    @GET("forecast.json")
    suspend fun getWeatherByLocation(@Query("key")key:String,@Query("q")latitudeLongitude:String,@Query("days")days:Int = 7):Response<WeatherResponse>
}