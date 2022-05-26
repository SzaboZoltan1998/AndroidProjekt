package com.example.androidproject.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproject.MyApplication
import com.example.androidproject.model.*
import com.example.androidproject.repository.Repository
import com.example.androidproject.utils.ToastError

class ForgotPasswordViewModel (val context: Context, val repository: Repository) : ViewModel()  {
    var user = MutableLiveData<User>()

    init {
        user.value=User()
    }


//    suspend fun resend(){
//        val request =
//            ResetPasswordRequest(username = user.value!!.username, email = user.value!!.email)
//        try {
//            val result = repository.resetPassword(request)
//            MyApplication.code = result.code
//            code.value = result.code
//            Log.d("xxx", "MyApplication - code:  ${MyApplication.code}")
//        } catch (e: Exception) {
//            ToastError.showtoast(context, e)
//            Log.d("xxx", "LoginViewModel - exception: ${e.toString()}")
//        }
//    }
//    suspend fun resendT(){
//        val request=TokenResetPasswordRequest(MyApplication.token,)
//    }
}