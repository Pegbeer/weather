package com.pegbeer.usecases.locations

import com.pegbeer.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface ILocationsUseCase {
    suspend fun getLocations(query:String): Flow<List<Location>>
}