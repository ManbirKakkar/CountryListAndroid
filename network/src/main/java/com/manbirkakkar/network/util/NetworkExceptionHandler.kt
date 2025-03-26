package com.manbirkakkar.network.util

import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import java.io.IOException

object NetworkExceptionHandler {
    fun handleException(e: Exception): String {
        return when (e) {
            is IOException -> "Network error occurred"
            is HttpException -> "HTTP error: ${e.code()}"
            is JsonDataException -> "Data parsing error"
            else -> "Unexpected error: ${e.localizedMessage}"
        }
    }
}