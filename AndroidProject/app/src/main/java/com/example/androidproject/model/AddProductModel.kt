package com.example.androidproject.model

data class AddProductModel(
    var title: String,
    var description: String,
    var price_per_unit: String,
    var unit: String,
    var is_active: Boolean,
    var rating: Double,
    var amount_type: String,
    var price_type: String
)
