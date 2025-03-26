package com.manbirkakkar.countrylistandroid.ui

import com.manbirkakkar.network.model.Country


sealed interface CountriesUiState {
    data object Loading : CountriesUiState
    data class Success(val countries: List<Country>) : CountriesUiState
    data class Error(val message: String?) : CountriesUiState
}