package com.example.androidproject.model

import com.squareup.moshi.JsonClass
import java.io.File

//import com.google.gson.annotations.SerializedName
/*
data class User(var username: String="", var password: String="", var email: String="", var phone_number: String=""){
    constructor( username: String="",  password: String="", email: String="", phone_number: String="",image: File): this()
}
*/

data class User(var username: String="", var password: String="", var email: String="", var phone_number: String="",var image :File?=File(""))

@JsonClass(generateAdapter = true)
data class LoginRequest (
    var username: String,
    var password: String
)

@JsonClass(generateAdapter = true)
data class LoginResponse (
    var username: String,
    var email: String,
    var phone_number: Int,
    var token: String,
    var creation_time: Long,
    var refresh_time: Long
)

@JsonClass(generateAdapter = true)
data class RegisterRequest(
    var username: String,
    var password: String,
    var email: String,
    var phone_number: String,
    var userImage: File

)

@JsonClass(generateAdapter = true)
data class RegisterResponse(
    var code: String,
    var message:String,
    var creation_time: Long
)
@JsonClass(generateAdapter = true)
data class Activate(
    var username: String
)
@JsonClass(generateAdapter = true)
data class RefreshToken(
    var username: String,
    var token: String,
    var creation_time: Long,
    var refresh_time: Long
)
@JsonClass(generateAdapter = true)
data class ResetPasswordRequest(
    var username: String,
    var email: String

)
@JsonClass(generateAdapter = true)
data class ResetPasswordResponse(
    var code: String,
    var message:String,
    var time_stamp: Long
)

@JsonClass(generateAdapter = true)
data class TokenResetPasswordRequest(
    var token: String,
    var password: String
)
@JsonClass(generateAdapter = true)
data class UpdateUserDataRequest(
    var phone_number: String,
    var email: String,
    var username: String,
    var image: File?
)
@JsonClass(generateAdapter = true)
data class UpdateUserDataRespons(
    var token: String
)
@JsonClass(generateAdapter = true)
data class GetUSerInfo(
    var username: String
)
// GSon converter
//data class LoginRequest (
//    @SerializedName("username")
//    var username: String,
//
//    @SerializedName("password")
//    var password: String
//)
//
//
//data class LoginResponse (
//    @SerializedName("username")
//    var username: String,
//
//    @SerializedName("email")
//    var email: String,
//
//    @SerializedName("phone_number")
//    var phone_number: Int,
//
//    @SerializedName("token")
//    var token: String,
//
//    @SerializedName("creation_time")
//    var creation_time: Long,
//
//    @SerializedName("refresh_time")
//    var refresh_time: Long
//)
