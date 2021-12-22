package com.example.androidproject.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproject.MyApplication
import com.example.androidproject.model.RegisterRequest
import com.example.androidproject.model.UpdateUserDataRequest
import com.example.androidproject.model.User
import com.example.androidproject.repository.Repository
import com.example.androidproject.utils.ToastError
import java.io.File

class ProfileViewModel (val context: Context, val repository: Repository) : ViewModel()  {
    var token: MutableLiveData<String> = MutableLiveData()
    var user = MutableLiveData<User>()

    init {
        user.value = User()
    }

    suspend fun change()
    {
        val request =
            UpdateUserDataRequest(phone_number= user.value!!.phone_number,
            email= user.value!!.email,username= user.value!!.username,image = user.value!!.image)
        try {
            val result = repository.change(request)
            MyApplication.token = result.token
            token.value = result.token
            Log.d("xxx", "MyApplication - token:  ${MyApplication.token}")
        } catch (e: Exception) {
            ToastError.showtoast(context, e)
            Log.d("xxx", "LoginViewModel - exception: ${e.toString()}")
        }

    }


}