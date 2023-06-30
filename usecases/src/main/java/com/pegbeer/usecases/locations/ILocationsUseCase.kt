package com.pegbeer.usecases.cities

import kotlinx.coroutines.flow.Flow

interface ICitiesUseCases {
    suspend fun save()
    suspend fun getCities(query:String): Flow<List<City>>
}