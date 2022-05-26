package com.example.androidproject.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("username")
    @Expose
    var username: String? = null,

    @SerializedName("email")
    @Expose
    var email: String? = null,

    @SerializedName("phone_number")
    @Expose
    var phoneNumber: Long? = null,

    @SerializedName("image_path")
    @Expose
    var imagePath: String? = null,

    @SerializedName("token")
    @Expose
    var token: String? = null,

    @SerializedName("creation_time")
    @Expose
    var creationTime: Long? = null,

    @SerializedName("refresh_time")
    @Expose
    var refreshTime: Int? = null
)