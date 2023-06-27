package com.pegbeer.usecases.cities

import com.pegbeer.domain.model.City
import kotlinx.coroutines.flow.Flow

interface ISaveCitiesUseCase {
    suspend fun save()
    suspend fun getCities(query:String): Flow<List<City>>
}