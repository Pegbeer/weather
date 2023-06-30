package com.pegbeer.usecases.locations

import com.pegbeer.domain.model.Location
import com.pegbeer.domain.repository.IWeatherRepository
import kotlinx.coroutines.flow.Flow

class LocationsUseCase(private val repository: IWeatherRepository) : ILocationsUseCase {

    override suspend fun getLocations(query: String): Flow<List<Location>> {
        return repository.getLocations(query)
    }
}