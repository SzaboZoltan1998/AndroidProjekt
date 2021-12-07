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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.androidproject.R
import com.example.androidproject.repository.Repository
import com.example.androidproject.viewmodels.ForgotPasswordViewModel
import com.example.androidproject.viewmodels.ForgotPasswordViewModelFactory
import com.example.androidproject.viewmodels.RegistrationViewModel
import com.example.androidproject.viewmodels.RegistrationViewModelFactory
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ForgotPasswordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
        val view = inflater.inflate(R.layout.fragment_registration, container, false)
        val editText1: EditText = view.findViewById(R.id.edittext_name_registration_fragment)
        val button: Button = view.findViewById(R.id.button_login_fragment)
        button.setOnClickListener {

            }
            lifecycleScope.launch {
                forgotPasswordViewModel.resend()
            }
        return view
        }
}
