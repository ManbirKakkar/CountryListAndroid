package com.manbirkakkar.countrylistandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manbirkakkar.countrylistandroid.databinding.ItemCountryBinding
import com.manbirkakkar.network.model.Country


class CountriesAdapter : ListAdapter<Country, CountriesAdapter.CountryViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CountryViewHolder(
        private val binding: ItemCountryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.apply {
                txtTitle.text = "${country.name}, ${country.region}"
                txtCode.text = country.code
                txtCapital.text = country.capital
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country) =
            oldItem.code == newItem.code

        override fun areContentsTheSame(oldItem: Country, newItem: Country) =
            oldItem == newItem
    }
}