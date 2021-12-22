package com.example.androidproject.repository

import android.net.http.HttpResponseCache
import com.example.androidproject.api.RetrofitInstance
import com.example.androidproject.model.*

class Repository {
    suspend fun login(request: LoginRequest): LoginResponse {
        return RetrofitInstance.api.login(request)
    }

    suspend fun getProducts(token: String): ProductResponse {
        return RetrofitInstance.api.getProducts(token)
    }
    suspend fun register(request: RegisterRequest): RegisterResponse {
        return RetrofitInstance.api.register(request)
    }

    suspend fun activate(username: String) : HttpResponseCache {
        return RetrofitInstance.api.activate(username)
    }
    suspend fun change(request: UpdateUserDataRequest):UpdateUserDataRespons{
        return RetrofitInstance.api.change(request)
    }
}