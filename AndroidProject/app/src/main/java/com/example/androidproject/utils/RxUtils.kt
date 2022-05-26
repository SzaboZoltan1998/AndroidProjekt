package com.example.androidproject.utils

import android.content.SharedPreferences
import android.app.Activity
import io.reactivex.disposables.Disposable
import android.text.Editable
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import com.example.androidproject.utils.DbHelper
import android.content.ContentValues

object RxUtils {
    fun disposeSilently(vararg disposables: Disposable?) {
        for (disposable in disposables) {
            if (disposable != null && !disposable.isDisposed) {
                disposable.dispose()
            }
        }
    }
}