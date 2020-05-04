package com.mosh.testgloballogic.data.repository

import com.mosh.testgloballogic.data.repository.data.LatopResponse
import retrofit2.Response
import retrofit2.http.GET

interface LatopApi {

    @GET("list")
    suspend fun getListLatops(): Response<LatopResponse>
}