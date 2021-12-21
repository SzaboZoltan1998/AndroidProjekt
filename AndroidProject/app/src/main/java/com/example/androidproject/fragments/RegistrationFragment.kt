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
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.adapters.DataAdapter
import com.example.androidproject.model.Product
import com.example.androidproject.repository.Repository
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidproject.viewmodels.*
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment() {
    private lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = RegistrationViewModelFactory(this.requireContext(), Repository())
        registrationViewModel = ViewModelProvider(this, factory).get(RegistrationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_registration, container, false)
        val editText1: EditText = view.findViewById(R.id.edittext_name_registration_fragment)
        val editText2: EditText = view.findViewById(R.id.edittext_name_email_fragment)
        val editText3: EditText = view.findViewById(R.id.edittext_phone_number_fragment)
        val editText4: EditText= view.findViewById(R.id.edittext_password_login_fragment)
        val button: Button = view.findViewById(R.id.button_login_fragment)
        button.setOnClickListener {
            registrationViewModel.user.value.let {
                if (it != null) {
                    it.username = editText1.text.toString()
                }
                if (it!=null)
                {
                    it.email=editText2.text.toString()
                }
                if (it!=null)
                {
                    it.phone_number=editText3.text.toString()
                }
                if (it != null) {
                    it.password = editText4.text.toString()
                }

            }
            lifecycleScope.launch {
                registrationViewModel.register()
            }

        }

        registrationViewModel.code.observe(viewLifecycleOwner){
            Log.d("xxx", "navigate to list")
            findNavController(view).navigate(R.id.action_loginFragment_to_listFragment)
            //findNavController(view).navigate()
        }
        return view
    }
}