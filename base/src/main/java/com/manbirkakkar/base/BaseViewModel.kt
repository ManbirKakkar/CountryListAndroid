package com.manbirkakkar.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {
    protected val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    protected val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    protected fun handleError(exception: Throwable) {
        _error.value = exception.localizedMessage ?: "Unknown error"
        _loading.value = false
    }
}