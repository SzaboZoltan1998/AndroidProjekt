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
    suspend fun activate()
    {
        /*
        try {
            val result=repository.activate(user.value!!.username)
            MyApplication.code=result.code
            code.value=result.code
            Log.d("xxx","Activate -token:${MyApplication.code}")

        }catch (e: Exception){
            ToastError.showtoast(context,e)
            Log.d("xxx","Activate -exception: ${e.toString()}")
        }*/
        val result=repository.activate(user.value!!.username)
        val File:File=File("D:\\OneDrive\\Documents\\GitHub\\AndroidProjekt\\AndroidProject\\app\\build\\generated\\res\\resValues\\debug\\activate.html")

        val file =getFileFromAssets(context,result.toString())
        val doc=Jsoup.parse(file,"UFT-8","")
        val element=doc.getElementByClass("container")
        Toast.makeText(context,element.text(),Toast.LENGTH_SHORT).show()


    }

    private fun getFileFromAssets(context: Context,fileName: String): File=File(context.cacheDir,fileName)
        .also {
        if(!it.exists()){
            it.outputStream().use{cache-> context.assets.open(fileName)
                .use { inputstream->inputstream.copyTo(cache)
                }
            }
        }

    }
}