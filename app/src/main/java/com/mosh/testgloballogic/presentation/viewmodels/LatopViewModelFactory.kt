package com.mosh.testgloballogic.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mosh.testgloballogic.data.repository.LatopRepository

@Suppress("UNCHECKED_CAST")
class LatopViewModelFactory(
    private val repository: LatopRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = LatopViewModel(repository) as T
}