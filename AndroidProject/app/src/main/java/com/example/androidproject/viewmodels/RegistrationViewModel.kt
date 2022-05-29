package com.example.androidproject.viewmodels

import android.content.Context
import android.net.http.HttpResponseCache
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproject.MyApplication
import com.example.androidproject.model.Activate
import com.example.androidproject.model.RegisterRequest
import com.example.androidproject.model.User
import com.example.androidproject.repository.Repository
import com.example.androidproject.utils.Navigator
import com.example.androidproject.utils.ToastError
import org.jsoup.Jsoup
import java.io.File




class RegistrationViewModel (val context: Context, val repository: Repository) : ViewModel()  {
    var user = MutableLiveData<User>()

    init {
        user.value = User()
    }

    suspend fun
            register(user: User)
    {
        val request =
            RegisterRequest(username = user.username, password = user.password,
            email = user.email,phone_number = user.phone_number)
        try {
            Log.d("3SS", "register: "+request.toString())
            repository.register(request)
            Log.d("xxx", "MyApplication - Register success")
            activate(request.username)
        } catch (e: Exception) {
            ToastError.showtoast(context, e)
            Log.d("xxx", "LoginViewModel - exception: ", e)

        }

    }

    private suspend fun activate(userName: String) {
        try {
            repository.activate(username = userName)
            Log.d("xxx", "MyApplication - Activate success")
            Navigator.getsInstance(context).goBack()
        } catch (e: Exception) {
            ToastError.showtoast(context, e)
            Log.d("xxx", "LoginViewModel - exception: $e")

        }
    }



}