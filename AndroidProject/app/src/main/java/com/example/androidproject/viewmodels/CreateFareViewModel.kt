package com.example.androidproject.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproject.model.AddProductModel
import com.example.androidproject.model.AddProductResponseModel
import com.example.androidproject.repository.Repository
import com.example.androidproject.utils.ToastError

class CreateFareViewModel(private val context: Context, private val repository: Repository) : ViewModel() {
    var responseModel:MutableLiveData<AddProductResponseModel> = MutableLiveData<AddProductResponseModel>()

    suspend fun addProduct(model: AddProductModel) {
        try {
            repository.setContext(context)
            responseModel.value = repository.addProduct(model)
        }catch (e: Exception) {
            ToastError.showtoast(context, e)
            Log.d("xxx", "LoginViewModel - exception: ", e)
        }
    }
}
