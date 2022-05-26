package com.example.androidproject.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproject.model.ProductResponse
import com.example.androidproject.model.TimeLineOrdersModel
import com.example.androidproject.repository.Repository
import com.example.androidproject.utils.SharedPrefUtils
import com.example.androidproject.utils.ToastError

class TimelineViewModel(private var context: Context, private var repository: Repository) : ViewModel() {

    var responseData : MutableLiveData<ProductResponse> = MutableLiveData()
    var myProducts: MutableLiveData<ProductResponse> = MutableLiveData()

    private val TAG = "TimelineViewModel"
    suspend fun getProducts() {
        try {
            responseData.value = repository.getProducts(SharedPrefUtils.readString(context, SharedPrefUtils.TOKEN)!!)
        } catch (e : Exception) {
            ToastError.showtoast(context, e)
            Log.d(TAG, "Error occured ", e)
        }
    }

    suspend fun getMyProducts() {
        try {

            val filter = "{\"username\" : " +
                    "\"" + SharedPrefUtils.readString(context, SharedPrefUtils.USERNAME) + "\"}"
            myProducts.value = repository.getMyProducts(
                SharedPrefUtils.readString(context, SharedPrefUtils.TOKEN)!!,
                filter
            )
        } catch (e : Exception) {
            ToastError.showtoast(context, e)
            Log.e(TAG, "Error occured ", e)
        }
    }
}