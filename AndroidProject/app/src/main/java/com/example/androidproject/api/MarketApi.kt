package com.example.androidproject.api

import com.example.androidproject.model.LoginRequest
import com.example.androidproject.model.LoginResponse
import com.example.androidproject.model.ProductResponse
import com.example.androidproject.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface MarketApi {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String): ProductResponse
}