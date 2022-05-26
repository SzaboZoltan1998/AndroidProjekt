package com.example.androidproject.model

import com.google.gson.annotations.SerializedName

class RegisterResponseModel(
    @field:SerializedName("code") private val mCode: Int,
    @field:SerializedName(
        "message"
    ) private val mMessage: String,
    @field:SerializedName("creation_time") private val mCreationTime: Long
)