package com.example.androidproject.repository

import android.content.Context
import android.net.http.HttpResponseCache
import android.util.Log
import com.example.androidproject.api.RetrofitInstance
import com.example.androidproject.model.*
import com.example.androidproject.utils.SharedPrefUtils

class Repository {

    private var mContext: Context? = null

    fun setContext(context: Context?) {
        mContext = context
    }

    suspend fun login(request: LoginModel): LoginResponseModel {
        return RetrofitInstance.api.login(request)
    }

    suspend fun getProducts(token: String): ProductResponse {
        return RetrofitInstance.api.getProducts(token)
    }

    suspend fun getMyProducts(token: String, filter: String): ProductResponse {
        return RetrofitInstance.api.getMyProducts(token, filter)
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
    suspend fun resetPassword(request: ResetPasswordRequest):ResetPasswordResponse{
        return RetrofitInstance.api.resetPassword(request)
    }
    suspend fun resetPasswordT(token: String,password:String):ResetPasswordResponse{
        return RetrofitInstance.api.resetPasswordT(token, password)
    }

    suspend fun addProduct(model: AddProductModel): AddProductResponseModel {
        Log.d("3SS", "addProduct: "+(mContext == null))
        return RetrofitInstance.api.addProduct(
            SharedPrefUtils.readString(mContext!!, SharedPrefUtils.TOKEN),
            model.title,
            model.description,
            model.price_per_unit,
            model.unit,
            model.is_active,
            model.rating,
            model.amount_type,
            model.price_type
        )
    }
}