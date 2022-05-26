package com.example.androidproject.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TimeLineOrdersModel(
    val item_count: Int,
    val products: List<TimeLineOrderModel> = listOf(),
    val timestamp: Long
)