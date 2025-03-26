package com.manbirkakkar.network.service

import com.manbirkakkar.network.model.Country
import retrofit2.http.GET

interface ApiService {
    @GET("countries.json")
    suspend fun getCountries(): List<Country>
}