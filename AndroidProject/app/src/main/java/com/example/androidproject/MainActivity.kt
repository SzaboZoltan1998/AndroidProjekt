package com.example.androidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    private val runnable:Runnable= Runnable {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        // Up navigation
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    public fun refreshToken(){
        handler= Handler()
        handler.postDelayed(runnable,TimeUnit.MINUTES.toMillis(2))
    }

    override fun onDestroy() {
        handler.removeCallbacks(runnable)
        super.onDestroy()
    }
}