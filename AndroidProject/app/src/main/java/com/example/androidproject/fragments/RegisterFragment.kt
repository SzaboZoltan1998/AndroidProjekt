package com.example.androidproject.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.androidproject.databinding.RegisterFragmentBinding
import com.example.androidproject.interfaces.MainFragmentListener
import com.example.androidproject.repository.Repository
import com.example.androidproject.viewmodels.RegistrationViewModel
import com.example.androidproject.viewmodels.RegistrationViewModelFactory
import kotlinx.coroutines.launch

class RegisterFragment() : Fragment() {

    private var mMainFragmentListener: MainFragmentListener? = null
    private var mBinding: RegisterFragmentBinding? = null
    private var mViewModel: RegistrationViewModel? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (arguments != null) {
            mMainFragmentListener = arguments?.getSerializable("KEY_MAIN_LISTENER") as MainFragmentListener?
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = RegistrationViewModelFactory(requireContext(), Repository())
        mViewModel = ViewModelProvider(this, factory).get(RegistrationViewModel::class.java)
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
            lifecycleScope.launch { mViewModel?.register() }
        }
    }

}