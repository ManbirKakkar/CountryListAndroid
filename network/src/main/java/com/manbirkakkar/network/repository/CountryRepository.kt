package com.manbirkakkar.network.repository

import com.manbirkakkar.network.model.Country
import com.manbirkakkar.network.service.ApiService

interface CountryRepository {
    suspend fun getCountries(): Result<List<Country>>
}

class CountryRepositoryImpl(
    private val countryService: ApiService
) : CountryRepository {
    override suspend fun getCountries(): Result<List<Country>> {
        return try {
            val response = countryService.getCountries() // Use injected service
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}