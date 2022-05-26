package com.example.androidproject.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.androidproject.R
import com.example.androidproject.repository.Repository
import com.example.androidproject.viewmodels.ActivationViewModel
import com.example.androidproject.viewmodels.ActivationViewModelFactory
import com.example.androidproject.viewmodels.LoginViewModel
import com.example.androidproject.viewmodels.LoginViewModelFactory
import kotlinx.coroutines.launch


class ActivationFragment : Fragment() {
//    private lateinit var activationFragment: ActivationViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val factory = ActivationViewModelFactory(this.requireContext(), Repository())
//        activationFragment = ViewModelProvider(this, factory).get(ActivationViewModel::class.java)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_activation, container, false)
//
//            lifecycleScope.launch {
//                activationFragment.activate()
//            }
//
//
//        return view
//    }
}