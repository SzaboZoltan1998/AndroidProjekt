package com.example.androidproject.api

import com.example.androidproject.model.*
import com.example.androidproject.utils.Constants
import okhttp3.ResponseBody
import retrofit2.http.*

interface MarketApi {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginModel): LoginResponseModel

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String): ProductResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun  getMyProducts(
        @Header("token") token: String,
        @Header("filter") filter: String
    ): ProductResponse

    @POST(Constants.REGISTER_URL)
    suspend fun register(@Body request: RegisterRequest) : RegisterResponse

    @GET(Constants.ACTIVATE_URL)
    suspend fun activate(@Query("username") userName: String) : ResponseBody

    @POST(Constants.CHANGE_PROFILE_URL)
    suspend fun change(@Body request: UpdateUserDataRequest):UpdateUserDataRespons

    @POST(Constants.RESET_PASSWORD_URL)
    suspend fun resetPassword(@Body request: ResetPasswordRequest):ResetPasswordResponse

    @GET(Constants.RESET_PASSWORD_URL)
    suspend fun resetPasswordT(@Header("token") token: String,@Header("password") password: String):ResetPasswordResponse

    @Multipart
    @POST("products/add")
    suspend fun addProduct(
        @Header("token") token: String?,
        @Part("title") title: String?,
        @Part("description") description: String?,
        @Part("price_per_unit") price_per_unit: String?,
        @Part("units") units: String?,
        @Part("is_active") is_active: Boolean?,
        @Part("rating") rating: Double?,
        @Part("amount_type") amount_type: String?,
        @Part("price_type") price_type: String?
    ): AddProductResponseModel
}