package com.pegbeer.usecases.cities

import com.pegbeer.domain.repository.IWeatherRepository
import kotlinx.coroutines.flow.Flow

class CitiesUseCases(private val repository: IWeatherRepository) : ICitiesUseCases {

    override suspend fun save() {
        repository.loadCities()
    }

    override suspend fun getCities(query: String): Flow<List<City>> {
        return repository.getLocations(query)
    }
}