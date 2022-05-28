package com.example.androidproject.model

import com.google.gson.annotations.SerializedName

data class UpdatedData (
    @SerializedName("_id"           ) var Id           : String?  = null,
    @SerializedName("username"      ) var username     : String?  = null,
    @SerializedName("password"      ) var password     : String?  = null,
    @SerializedName("phone_number"  ) var phoneNumber  : Int?     = null,
    @SerializedName("email"         ) var email        : String?  = null,
    @SerializedName("is_activated"  ) var isActivated  : Boolean? = null,
    @SerializedName("image_url"     ) var imageUrl     : String?  = null,
    @SerializedName("image_id"      ) var imageId      : String?  = null,
    @SerializedName("creation_time" ) var creationTime : String?  = null,
    @SerializedName("__v"           ) var _v           : Int?     = null
)
