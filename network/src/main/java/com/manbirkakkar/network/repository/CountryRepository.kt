package com.manbirkakkar.network.repository

import com.manbirkakkar.network.RetrofitClient
import com.manbirkakkar.network.model.Country

interface CountryRepository {
    suspend fun getCountries(): Result<List<Country>>
}

class CountryRepositoryImpl : CountryRepository {
    override suspend fun getCountries(): Result<List<Country>> {
        return try {
            val response = RetrofitClient.apiService.getCountries()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}