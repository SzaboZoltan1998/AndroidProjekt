package com.example.androidproject.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
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
import com.example.androidproject.databinding.RegisterFragmentBinding
import com.example.androidproject.interfaces.MainFragmentListener
import com.example.androidproject.model.User
import com.example.androidproject.viewmodels.*
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment() {
    private lateinit var registrationViewModel: RegistrationViewModel
    private var mBinding: RegisterFragmentBinding? = null
    private var mMainFragmentListener: MainFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (arguments != null) {
            mMainFragmentListener = arguments?.getSerializable("KEY_MAIN_LISTENER") as MainFragmentListener?
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = RegistrationViewModelFactory(this.requireContext(), Repository())
        registrationViewModel = ViewModelProvider(this, factory).get(RegistrationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mBinding == null) {
            mBinding = RegisterFragmentBinding.inflate(inflater, container, false)
            initViews()
        }

        mMainFragmentListener?.showLoginIcons()
        mMainFragmentListener?.hideBottomNav()
        return mBinding?.root
    }

    private fun initViews() {
        mBinding!!.registerButton.setOnClickListener {
            val user = User(
                mBinding!!.userNameEt.text.toString(),
                mBinding!!.passwordEt.text.toString(),
                mBinding!!.emailEt.text.toString(),
                mBinding!!.phoneNumberEt.text.toString()
            )
            lifecycleScope.launch {
                registrationViewModel.register(user)
            }
        }
    }
}