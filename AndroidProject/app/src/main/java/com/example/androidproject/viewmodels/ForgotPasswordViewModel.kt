package com.example.androidproject.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproject.MyApplication
import com.example.androidproject.model.RegisterRequest
import com.example.androidproject.model.User
import com.example.androidproject.repository.Repository

class ForgotPasswordViewModel (val context: Context, val repository: Repository) : ViewModel()  {


    init {

    }

    suspend fun resend()
    {

    }
}