package com.example.androidproject.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TimeLineOrderModel(
    @Json(name = "rating"         ) var rating       : Double?           = null,
    @Json(name = "amount_type"    ) var amountType   : String?           = null,
    @Json(name = "price_type"     ) var priceType    : String?           = null,
    @Json(name = "product_id"     ) var productId    : String?           = null,
    @Json(name = "username"       ) var username     : String?           = null,
    @Json(name = "is_active"      ) var isActive     : Boolean?          = null,
    @Json(name = "price_per_unit" ) var pricePerUnit : String?           = null,
    @Json(name = "units"          ) var units        : String?           = null,
    @Json(name = "description"    ) var description  : String?           = null,
    @Json(name = "title"          ) var title        : String?           = null,
    @Json(name = "images"         ) var images       : List<String> = listOf(),
    @Json(name = "creation_time"  ) var creationTime : Long?              = null,
    @Json(name = "messages"       ) var messages     : List<String> = listOf()
)