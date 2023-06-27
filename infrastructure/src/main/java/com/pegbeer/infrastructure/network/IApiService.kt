package com.pegbeer.infrastructure.network

import com.pegbeer.domain.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface IApiService {

    @GET
    suspend fun getWeatherByLocation(@Url url:String,@Query("q")city:String,@Query("appid")appId:String):Response<WeatherResponse>
}