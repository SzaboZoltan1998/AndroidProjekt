package com.example.androidproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidproject.utils.DbHelper
import com.example.androidproject.utils.Navigator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DbHelper.getInstance(this)
        dbHelper?.insertImages(1,"zoli1234_1652894358422", "https://s13emagst.akamaized.net/products/27550/27549040/images/res_af698aa2e8ed783feb1e6fdc57500645.png?width=450&height=450&hash=8E71BFDCA4BF6CE3571C5CA5E2929618")
        dbHelper?.insertImages(2,"zoli1234_1652894358422", "https://s13emagst.akamaized.net/products/27550/27549040/images/res_bac9b0ab34c51c7a55ad9b1c7ec49139.jpg?width=450&height=450&hash=5941FA6CA74C7AF5C882060DA504F3AD")
        dbHelper?.insertImages(3, "zoli1234_1652894358422", "https://s13emagst.akamaized.net/products/27534/27533703/images/res_a6a0735d2b4fc9d1718e7c2531f5971d.jpg?width=450&height=450&hash=511176198A9141B2D5079931AB3E0EEE")
        dbHelper?.insertImages(4, "zoli1234_1652894358422", "https://s13emagst.akamaized.net/products/27534/27533703/images/res_36f7c5bd091e955099ca11c3bdf34ae2.jpg?width=450&height=450&hash=81C98750D7CDBE83713EC6A00582EE10")

        Navigator.getsInstance(this).replace()
    }

    override fun onBackPressed() {
        val navController = Navigator.getsInstance(this).getNavController()

        if (navController != null) {
            navController.navigateUp()
            return
        }

        super.onBackPressed()
    }

}