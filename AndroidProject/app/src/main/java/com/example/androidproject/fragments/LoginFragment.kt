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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.adapters.DataAdapter
import com.example.androidproject.model.Product
import com.example.androidproject.repository.Repository
import com.example.androidproject.viewmodels.ListViewModel
import com.example.androidproject.viewmodels.ListViewModelFactory
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidproject.databinding.LoginFragmentBinding
import com.example.androidproject.interfaces.MainFragmentListener
import com.example.androidproject.model.LoginModel
import com.example.androidproject.utils.Navigator
import com.example.androidproject.utils.SharedPrefUtils
import com.example.androidproject.viewmodels.LoginViewModel
import com.example.androidproject.viewmodels.LoginViewModelFactory
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private val TAG = "LoginFragment"
    private lateinit var loginViewModel: LoginViewModel
    private var mBinding: LoginFragmentBinding? = null
    private var mMainFragmentListener: MainFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (arguments != null) {
            mMainFragmentListener = arguments?.getSerializable("KEY_MAIN_LISTENER") as MainFragmentListener?
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = LoginViewModelFactory(this.requireContext(), Repository())
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mBinding == null) {
            mBinding = LoginFragmentBinding.inflate(inflater, container, false)
            initViews()
        }

        mMainFragmentListener!!.hideBottomNav()
        mMainFragmentListener!!.showLoginIcons()
        return mBinding?.root
    }

    private fun initViews() {
        mBinding!!.signUp.setOnClickListener {
            Navigator.getsInstance(requireContext()).showRegisterFragment()
        }

        mBinding!!.clickHere.setOnClickListener {
            Navigator.getsInstance(requireContext()).showForgotPasswordFragment()
        }

        mBinding!!.loginButton.setOnClickListener {
            loginViewModel.setDataModel(LoginModel(
                mBinding!!.userNameEt.text.toString(),
                mBinding!!.passwordEt.text.toString()
            ))

            lifecycleScope.launch {
                loginViewModel.login()
            }
        }

    }
}