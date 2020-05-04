package com.mosh.testgloballogic.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mosh.testgloballogic.data.repository.LatopRepository
import com.mosh.testgloballogic.data.repository.data.LatopResponse
import com.mosh.testgloballogic.utils.NetworkStatus
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import java.lang.Exception

class LatopViewModel(
    private val repository: LatopRepository
) : ViewModel() {
    fun getLatops() = liveData(Dispatchers.IO) {
        emit(NetworkStatus.loading(null))
        try {
            val retrieved: Response<LatopResponse> = repository.getListLatops()

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