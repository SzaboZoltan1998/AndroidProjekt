package com.example.androidproject.model

import com.google.gson.annotations.SerializedName

data class UpdateUserDataResponse (
    @SerializedName("code"        ) var code        : Int?         = null,
    @SerializedName("updatedData" ) var updatedData : UpdatedData? = UpdatedData(),
    @SerializedName("timestamp"   ) var timestamp   : Long?         = null
)