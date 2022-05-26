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
import android.util.Log
import java.util.ArrayList

class DbHelper private constructor(context: Context) :
    SQLiteOpenHelper(context, "image.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "create table " + IMAGE_TABLE_NAME + "(" +
                    IMAGE_COLUMN_ID + " integer primary key, " +
                    IMAGE_COLUMN_PRODUCT_ID + " text, " +
                    IMAGE_COLUMN_IMAGE_URL + " text)"
        )
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        database.execSQL("DROP TABLE IF EXISTS " + IMAGE_TABLE_NAME)
        onCreate(database)
    }

    fun insertImages(id: Int, productId: String?, url: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(IMAGE_COLUMN_ID, id)
        contentValues.put(IMAGE_COLUMN_PRODUCT_ID, productId)
        contentValues.put(IMAGE_COLUMN_IMAGE_URL, url)
        db.insert(IMAGE_TABLE_NAME, null, contentValues)
        return true
    }

    val data: ArrayList<ImageModel>
        get() {
            val images = ArrayList<ImageModel>()
            val db = this.readableDatabase
            val cursor = db.rawQuery("select * from  " + IMAGE_TABLE_NAME, null)
            if (cursor != null) {
                cursor.moveToFirst()
                while (cursor.moveToNext()) {
                    Log.d("3SS", "getData: " + cursor.getString(1) + " " + cursor.getString(2))
                    val productId = cursor.getString(1)
                    val url = cursor.getString(2)
                    images.add(ImageModel(productId, url))
                }
                cursor.close()
            }
            return images
        }

    companion object {
        private var sInstance: DbHelper? = null
        const val IMAGE_TABLE_NAME = "image"
        const val IMAGE_COLUMN_ID = "id"
        const val IMAGE_COLUMN_PRODUCT_ID = "product_id"
        const val IMAGE_COLUMN_IMAGE_URL = "iamge_url"
        fun getInstance(context: Context): DbHelper? {
            if (sInstance == null) {
                sInstance = DbHelper(context)
            }
            return sInstance
        }
    }
}