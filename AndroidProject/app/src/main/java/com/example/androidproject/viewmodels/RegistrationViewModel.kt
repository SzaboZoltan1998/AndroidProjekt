package com.example.androidproject.viewmodels

import android.content.Context
import android.net.http.HttpResponseCache
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproject.MyApplication
import com.example.androidproject.model.RegisterRequest
import com.example.androidproject.model.User
import com.example.androidproject.repository.Repository
import com.example.androidproject.utils.ToastError
import org.jsoup.Jsoup
import java.io.File




class RegistrationViewModel (val context: Context, val repository: Repository) : ViewModel()  {
    var code: MutableLiveData<String> = MutableLiveData()
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
            MyApplication.code = result.code
            code.value = result.code
            Log.d("xxx", "MyApplication - token:  ${MyApplication.code}")
        } catch (e: Exception) {
            ToastError.showtoast(context, e)
            Log.d("xxx", "LoginViewModel - exception: ${e.toString()}")

        }

    }



}