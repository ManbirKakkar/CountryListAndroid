package com.manbirkakkar.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment : Fragment() {
    protected inline fun <reified T : ViewModel> createViewModel(
        factory: ViewModelProvider.Factory? = null
    ): T {
        return factory?.let {
            ViewModelProvider(this, it)[T::class.java]
        } ?: ViewModelProvider(this)[T::class.java]
    }
}