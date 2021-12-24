package com.example.androidproject.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.androidproject.R
import com.example.androidproject.repository.Repository
import com.example.androidproject.viewmodels.ForgotPasswordViewModel
import com.example.androidproject.viewmodels.ForgotPasswordViewModelFactory
import com.example.androidproject.viewmodels.RegistrationViewModel
import com.example.androidproject.viewmodels.RegistrationViewModelFactory
import kotlinx.coroutines.launch

class ForgotPasswordFragment : Fragment() {
    private lateinit var forgotPasswordViewModel: ForgotPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ForgotPasswordViewModelFactory(this.requireContext(), Repository())
        forgotPasswordViewModel = ViewModelProvider(this, factory).get(ForgotPasswordViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_forgot_password, container, false)
        val textview1:TextView=view.findViewById(R.id.forgot_view1)
        val textview2:TextView=view.findViewById(R.id.forgot_view2)
        val editText1: EditText = view.findViewById(R.id.edittext_email_forgotp_fragment)
        val editText2: EditText = view.findViewById(R.id.edittext_username_forgotp_fragment)
        val button: Button = view.findViewById(R.id.button_forgot_login_fragment)
        button.setOnClickListener {
            forgotPasswordViewModel.user.value.let{
                if (it!=null){
                    it.email=editText1.toString()
                    it.username=editText2.toString()
                }
            }

            }
            lifecycleScope.launch {
                forgotPasswordViewModel.resend()
            }
        return view
        }
}
