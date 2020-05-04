package com.mosh.testgloballogic.data.repository

import com.mosh.testgloballogic.data.repository.data.LaptopResponse
import retrofit2.Response
import retrofit2.http.GET

interface LaptopApi {

    @GET("list")
    suspend fun getListLaptops(): Response<LaptopResponse>
}