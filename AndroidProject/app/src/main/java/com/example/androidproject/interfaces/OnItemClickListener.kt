package com.example.androidproject.interfaces

import com.example.androidproject.model.AddProductModel
import com.example.androidproject.model.Product
import com.example.androidproject.model.TimeLineOrderModel

interface OnItemClickListener {
    fun onItemLongClick(model : AddProductModel)
    fun onItemLongClick(model : Product)
}