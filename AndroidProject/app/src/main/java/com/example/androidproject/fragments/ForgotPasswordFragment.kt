package com.example.androidproject.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.androidproject.R
import com.example.androidproject.databinding.ForgotPasswordFragmentBinding
import com.example.androidproject.interfaces.MainFragmentListener
import com.example.androidproject.repository.Repository
import com.example.androidproject.viewmodels.ForgotPasswordViewModel
import com.example.androidproject.viewmodels.ForgotPasswordViewModelFactory

class ForgotPasswordFragment : Fragment() {
    private lateinit var forgotPasswordViewModel: ForgotPasswordViewModel
    private var mBinding: ForgotPasswordFragmentBinding? = null
    private var mMainFragmentListener: MainFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (arguments != null) {
            mMainFragmentListener = arguments?.getSerializable("KEY_MAIN_LISTENER") as MainFragmentListener?
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ForgotPasswordViewModelFactory(this.requireContext(), Repository())
        forgotPasswordViewModel = ViewModelProvider(this, factory).get(ForgotPasswordViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (mBinding == null) {
            mBinding = ForgotPasswordFragmentBinding.inflate(inflater, container, false)
        }

        mMainFragmentListener?.hideBottomNav()
        mMainFragmentListener?.showLoginIcons()
        return mBinding!!.root
    }
}
