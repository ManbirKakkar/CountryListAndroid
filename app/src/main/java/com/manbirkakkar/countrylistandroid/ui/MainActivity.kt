package com.manbirkakkar.countrylistandroid.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.manbirkakkar.base.BaseActivity
import com.manbirkakkar.countrylistandroid.adapter.CountriesAdapter
import com.manbirkakkar.countrylistandroid.databinding.ActivityMainBinding
import com.manbirkakkar.network.model.Country
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter by lazy { CountriesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = this@MainActivity.adapter
        setHasFixedSize(true)
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { viewModel.uiState.collect { handleUiState(it) } }
                launch { viewModel.loading.collect { binding.progressBar.isVisible = it } }
            }
        }
    }

    private fun handleUiState(state: CountriesUiState) {
        when (state) {
            is CountriesUiState.Loading -> showLoading()
            is CountriesUiState.Success -> showCountries(state.countries)
            is CountriesUiState.Error -> showError(state.message)
        }
    }

    private fun showLoading() = with(binding) {
        recyclerView.isVisible = false
        errorMessage.isVisible = false
    }

    private fun showCountries(countries: List<Country>) = with(binding) {
        recyclerView.isVisible = true
        errorMessage.isVisible = false
        adapter.submitList(countries)
    }

    private fun showError(message: String?) = with(binding) {
        recyclerView.isVisible = false
        errorMessage.isVisible = true
        errorMessage.text = message ?: "Unknown error occurred"
    }
}
