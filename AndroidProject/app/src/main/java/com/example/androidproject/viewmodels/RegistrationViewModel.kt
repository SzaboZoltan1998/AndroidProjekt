package com.example.androidproject.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproject.MyApplication
import com.example.androidproject.model.LoginRequest
import com.example.androidproject.model.RegisterRequest
import com.example.androidproject.model.User
import com.example.androidproject.repository.Repository
import java.io.File

class RegistrationViewModel (val context: Context, val repository: Repository) : ViewModel()  {
    var user = MutableLiveData<User>()

    init {
        user.value = User()
    }

    suspend fun register()
    {
        val request =
            RegisterRequest(username = user.value!!.username, password = user.value!!.password,
            email = user.value!!.email,phone_number = user.value!!.phone_number,userImage = File("")
            )
        try {
            val result = repository.register(request)
            //Log.d("xxx", "MyApplication - token:  ${}")
        } catch (e: Exception) {
            Log.d("xxx", "LoginViewModel - exception: ${e.toString()}")
        }

    }
}