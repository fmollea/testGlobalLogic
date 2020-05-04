package com.mosh.testgloballogic.data.repository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LatopRepository {

    private val client = Retrofit.Builder()
        .baseUrl("http://private-f0eea-mobilegllatam.apiary-mock.com/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
        .create(LatopApi::class.java)

    suspend fun getListLatops() = client.getListLatops()
}