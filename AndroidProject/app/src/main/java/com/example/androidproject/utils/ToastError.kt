package com.example.androidproject.utils
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import java.lang.Exception

class ToastError {

    companion object {
        fun showtoast(context: Context, exception: Exception){
            Toast.makeText(context,exception.toString(),Toast.LENGTH_SHORT).show()
        }
    }


}