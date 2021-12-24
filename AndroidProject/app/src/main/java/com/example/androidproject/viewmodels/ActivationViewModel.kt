package com.example.androidproject.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproject.model.User
import com.example.androidproject.repository.Repository
import com.example.androidproject.utils.ToastError
import org.jsoup.Jsoup


class ActivationViewModel (val context: Context, val repository: Repository) : ViewModel()  {
    var token: MutableLiveData<String> = MutableLiveData()
    var user = MutableLiveData<User>()

    init {
        user.value= User()
    }

    suspend fun activate()
    {
        try{
            val result=repository.activate(user.value!!.username)
            val file =result.toString()
            val doc= Jsoup.parse(file)
            val element=doc.getElementsByClass("container")
            Toast.makeText(context,element.text(), Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
            ToastError.showtoast(context,e)
            Log.d("xxx","Activate -exception: ${e.toString()}")
        }

    }
}