package com.example.androidproject.utils

import android.content.SharedPreferences
import android.app.Activity
import io.reactivex.disposables.Disposable
import android.text.Editable
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import com.example.androidproject.utils.DbHelper
import android.content.ContentValues
import android.content.Context

object SharedPrefUtils {
    const val USERNAME = "USERNAME"
    const val EMAIL = "EMAIL"
    const val PASSWORD = "PASSWORD"
    const val IMAGE_PATH = "IMAGE_PATH"
    const val PHONE_NUMBER = "PHONE_NUMBER"
    const val TOKEN = "TOKEN"
    const val CREATION_TIME = "CREATION_TIME"
    const val REFRESH_TIME = "REFRESH_TIME"
    fun save(context: Context, itemLabel: String?, item: String?) {
        if (item == null) {
            return
        }
        val sharedPref = (context as Activity).getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(itemLabel, item)
        editor.apply()
    }

    fun save(context: Context, itemLabel: String?, item: Long?) {
        if (item == null) {
            return
        }
        val sharedPref = (context as Activity).getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putLong(itemLabel, item)
        editor.apply()
    }

    fun save(context: Context, itemLabel: String, refreshTime: Int?) {
        if (refreshTime == null) {
            return
        }
        val sharedPref = (context as Activity).getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt(itemLabel, refreshTime)
        editor.apply()
    }

    fun readString(context: Context, itemLabel: String?): String? {
        val sharedPref = (context as Activity).getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getString(itemLabel, "")
    }

    fun readLong(context: Context, itemLabel: String?): Long {
        val sharedPref = (context as Activity).getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getLong(itemLabel, 0)
    }

    fun readInt(context: Context, itemLabel: String?): Int {
        val sharedPref = (context as Activity).getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getInt(itemLabel, 0)
    }
}