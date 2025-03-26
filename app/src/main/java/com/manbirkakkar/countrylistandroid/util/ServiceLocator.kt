package com.manbirkakkar.countrylistandroid.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.manbirkakkar.countrylistandroid.ui.MainViewModel
import com.manbirkakkar.network.RetrofitClient
import com.manbirkakkar.network.repository.CountryRepository
import com.manbirkakkar.network.repository.CountryRepositoryImpl
import com.manbirkakkar.network.service.ApiService

object ServiceLocator {
    private val retrofitClient: RetrofitClient by lazy { RetrofitClient }

    val apiService: ApiService by lazy {
        retrofitClient.apiService
    }

    val countryRepository: CountryRepository by lazy {
        CountryRepositoryImpl(apiService)
    }

    fun createMainViewModel(owner: ViewModelStoreOwner): MainViewModel {
        return ViewModelProvider(
            owner,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MainViewModel(countryRepository) as T
                }
            }
        )[MainViewModel::class.java]
    }
}