package com.oguzel.marsrealestate

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MarsApi {

    private const val BASE_URL = "https://mars.udacity.com/"

    val moshi = Moshi.Builder().build()

    val retrofit = Retrofit.Builder()
        // .addConverterFactory(ScalarsConverterFactory.create()) // String response
        // .addConverterFactory(MoshiConverterFactory.create(moshi)) // Convert JSON
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}