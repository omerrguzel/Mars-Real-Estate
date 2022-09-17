package com.oguzel.marsrealestate

import com.oguzel.marsrealestate.data.MarsInfoItem
import retrofit2.Call
import retrofit2.http.GET

/**
 * Initialization of endpoint
 */
interface MarsApiService {
    @GET("realestate")
    fun getProperties(): Call<List<MarsInfoItem>>
}