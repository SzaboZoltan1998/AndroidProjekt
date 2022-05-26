package com.example.androidproject.model

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("_id"        ) var Id        : String? = null,
    @SerializedName("image_id"   ) var imageId   : String? = null,
    @SerializedName("image_name" ) var imageName : String? = null,
    @SerializedName("image_path" ) var imagePath : String? = null
)
