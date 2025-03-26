package com.manbirkakkar.countrylistandroid

import com.manbirkakkar.network.RetrofitClient
import com.manbirkakkar.network.repository.CountryRepository
import com.manbirkakkar.network.repository.CountryRepositoryImpl
import com.manbirkakkar.network.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCountryService(): ApiService {
        return RetrofitClient.apiService
    }

    @Provides
    @Singleton
    fun provideCountryRepository(
        apiService: ApiService
    ): CountryRepository {
        return CountryRepositoryImpl(apiService)
    }
}