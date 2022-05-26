package com.example.androidproject.model

import com.google.gson.annotations.SerializedName

data class AddProductResponseModel (
    @SerializedName("creation"       ) var creation     : String?           = null,
    @SerializedName("product_id"     ) var productId    : String?           = null,
    @SerializedName("username"       ) var username     : String?           = null,
    @SerializedName("is_active"      ) var isActive     : Boolean?          = null,
    @SerializedName("price_per_unit" ) var pricePerUnit : String?           = null,
    @SerializedName("units"          ) var units        : String?           = null,
    @SerializedName("description"    ) var description  : String?           = null,
    @SerializedName("title"          ) var title        : String?           = null,
    @SerializedName("images"         ) var images       : List<Images> = listOf(),
    @SerializedName("creation_time"  ) var creationTime : Long?              = null
)