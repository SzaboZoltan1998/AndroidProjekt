package com.example.androidproject.fragments

import androidx.fragment.app.Fragment

class BaseFragment : Fragment() {

    fun onBackPressed() : Boolean {
        return false
    }
}