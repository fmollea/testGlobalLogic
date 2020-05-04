package com.mosh.testgloballogic.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mosh.testgloballogic.data.repository.LaptopRepository

@Suppress("UNCHECKED_CAST")
class LaptopViewModelFactory(
    private val repository: LaptopRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = LaptopViewModel(repository) as T
}