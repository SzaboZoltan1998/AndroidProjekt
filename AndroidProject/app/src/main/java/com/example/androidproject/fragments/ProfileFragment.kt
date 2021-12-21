package com.example.androidproject.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidproject.R
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.drawToBitmap
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import com.example.androidproject.repository.Repository
import com.example.androidproject.viewmodels.LoginViewModel
import com.example.androidproject.viewmodels.LoginViewModelFactory
import com.example.androidproject.viewmodels.ProfileViewModel
import com.example.androidproject.viewmodels.ProfileViewModelFactory
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ProfileViewModelFactory(this.requireContext(), Repository())
        profileViewModel=ViewModelProvider(this,factory).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val imageView:ImageView = view.findViewById(R.id.profile_picture_profile)
        val editText1: EditText = view.findViewById(R.id.edittext_name_profile)
        val editText2: EditText = view.findViewById(R.id.edittext_phone_number_profile)
        val textView: TextView=view.findViewById(R.id.textview_email_profile)
        val button: Button = view.findViewById(R.id.button_change_profile)
        if (profileViewModel.user!=null) {
            editText1.setText(profileViewModel.user.value.username)
            editText2.setText(profileViewModel.user.value.phone_number)
            textView.setText(profileViewModel.user.value.email)
            //view.setBackgroundResource(profileViewModel.user.value.image)
            if(profileViewModel.user.value.image!=null) imageView.setImageURI(profileViewModel.user.value.image?.toURI())
        }
        button.setOnClickListener {
            profileViewModel.user.value.let {
                if (it != null) {
                    it.username=editText1.toString()
                }
                if (it != null) {
                    it.phone_number=editText2.toString()
                }

                if(it!=null){
                    it.image=imageView.ima
                }



            }
            lifecycleScope.launch {
                profileViewModel.login()
            }

        }
        profileViewModel.token.observe(viewLifecycleOwner){
            Log.d("xxx", "navigate to list")
            findNavController(view).navigate(R.id.action_loginFragment_to_listFragment)
        }
        return view
    }

}