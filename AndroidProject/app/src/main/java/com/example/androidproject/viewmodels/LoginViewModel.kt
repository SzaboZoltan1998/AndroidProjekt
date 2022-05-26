package com.example.androidproject.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproject.MainActivity
import com.example.androidproject.MyApplication
import com.example.androidproject.MyApplication.Companion.token
import com.example.androidproject.model.LoginModel
import com.example.androidproject.model.LoginRequest
import com.example.androidproject.model.User
import com.example.androidproject.repository.Repository
import com.example.androidproject.utils.Navigator
import com.example.androidproject.utils.SharedPrefUtils
import com.example.androidproject.utils.ToastError

class LoginViewModel(val context: Context, val repository: Repository) : ViewModel() {
    private val TAG = "LoginViewModel"
    private var mLoginData : MutableLiveData<LoginModel>? = null

    fun setDataModel(loginModel: LoginModel) {
        mLoginData = MutableLiveData(loginModel)
    }

    suspend fun login() {
        try {

            Log.d(TAG, "login: "+mLoginData?.toString())
            val model = repository.login(mLoginData!!.value!!)
            Log.d(TAG, "Login succeeded" + (model.toString()))
            SharedPrefUtils.save(context, SharedPrefUtils.USERNAME ,model.username)
            SharedPrefUtils.save(context, SharedPrefUtils.EMAIL, model.email)
            SharedPrefUtils.save(context, SharedPrefUtils.TOKEN, model.token)
            SharedPrefUtils.save(context, SharedPrefUtils.IMAGE_PATH,model.imagePath)
            SharedPrefUtils.save(context, SharedPrefUtils.CREATION_TIME, model.creationTime)
            SharedPrefUtils.save(context, SharedPrefUtils.REFRESH_TIME, model.refreshTime)

            Navigator.getsInstance(context).showTimelineFragment()
        } catch (e: Exception) {
            ToastError.showtoast(context, e)
            Log.d("xxx", "LoginViewModel - exception: ", e)
        }
    }
}