package com.mosh.testgloballogic.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mosh.testgloballogic.data.repository.LaptopRepository
import com.mosh.testgloballogic.data.repository.data.LaptopResponse
import com.mosh.testgloballogic.utils.NetworkStatus
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import java.lang.Exception

class LaptopViewModel(
    private val repository: LaptopRepository
) : ViewModel() {
    fun getLaptops() = liveData(Dispatchers.IO) {
        emit(NetworkStatus.loading(null))
        try {
            val retrieved: Response<LaptopResponse> = repository.getListLaptops()

            if (retrieved.isSuccessful) {
                emit(NetworkStatus.success(retrieved.body()))
            } else {
                emit(NetworkStatus.error(""))
            }
        } catch (e: Exception) {
            emit(NetworkStatus.error(""))
        }
    }
}