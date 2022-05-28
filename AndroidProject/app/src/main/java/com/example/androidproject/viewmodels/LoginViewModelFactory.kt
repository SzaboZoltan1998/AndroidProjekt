package com.example.androidproject.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidproject.model.LoginModel
import com.example.androidproject.repository.Repository
import kotlin.math.log

class LoginViewModelFactory(private val context: Context, private val repository: Repository) : ViewModelProvider.Factory {
    private var mLoginViewModel: LoginViewModel? = null

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        mLoginViewModel = LoginViewModel(context, repository)
        return mLoginViewModel as (T)
    }

    fun setDataModel(loginModel: LoginModel) {
        mLoginViewModel?.setDataModel(loginModel)
    }
}