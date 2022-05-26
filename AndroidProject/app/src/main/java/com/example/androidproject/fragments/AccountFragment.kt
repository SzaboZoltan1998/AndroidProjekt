package com.example.androidproject.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.androidproject.databinding.AccountFragmentBinding
import com.example.androidproject.interfaces.MainFragmentListener
import com.example.androidproject.repository.Repository
import com.example.androidproject.utils.SharedPrefUtils
import com.example.androidproject.viewmodels.AccountViewModel
import com.example.androidproject.viewmodels.AccountViewModelFactory
import kotlinx.coroutines.launch

class AccountFragment : Fragment() {
    private var mBinding: AccountFragmentBinding? = null
    private var username: String? = null
    private var mMainFragmentListener: MainFragmentListener? = null
    private var mViewModel: AccountViewModel? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (arguments != null) {
            username = requireArguments().getString("KEY_USERNAME")
            mMainFragmentListener = requireArguments().getSerializable("KEY_MAIN_LISTENER") as MainFragmentListener?
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = AccountViewModelFactory(requireContext(), Repository())
        mViewModel = ViewModelProvider(this, factory).get(AccountViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (mBinding == null) {
            mBinding = AccountFragmentBinding.inflate(inflater, container, false)
            initView()
        }
        return mBinding!!.root
    }

    private fun initView() {
        mMainFragmentListener?.showAccIcons()
        mMainFragmentListener?.hideBottomNav()
        if (username != null) {
            mBinding!!.editButton.visibility = View.GONE
            mBinding!!.userNameEt.isTextInputLayoutFocusedRectEnabled = true
            mBinding!!.userNameEt.setText(username)
            mBinding!!.profileName.text = username
            return
        }
        mBinding!!.emailEt.isTextInputLayoutFocusedRectEnabled = true
        mBinding!!.emailEt.setText(SharedPrefUtils.readString(requireContext(), SharedPrefUtils.EMAIL))
        mBinding!!.userNameEt.isTextInputLayoutFocusedRectEnabled = true
        mBinding!!.userNameEt.setText(SharedPrefUtils.readString(requireContext(), SharedPrefUtils.USERNAME))
        mBinding!!.profileName.text = SharedPrefUtils.readString(requireContext(), SharedPrefUtils.USERNAME)
        mBinding!!.phoneNumberEt.isTextInputLayoutFocusedRectEnabled = true
        mBinding!!.phoneNumberEt.setText(
            SharedPrefUtils.readString(
                requireContext(),
                SharedPrefUtils.PHONE_NUMBER
            )
        )
        mBinding!!.editButton.visibility = View.VISIBLE
        mBinding!!.editButton.setOnClickListener { v: View? ->
//            if (TextUtils.isEmpty(mBinding!!.emailEt.text) ||
//                TextUtils.isEmpty(mBinding!!.userNameEt.text) ||
//                TextUtils.isEmpty(mBinding!!.phoneNumberEt.text)
//            ) {
//                Toast.makeText(context, "Must insert value in every cell", Toast.LENGTH_SHORT)
//                    .show()
//                return@setOnClickListener
//            }
//            val phone = trimString(mBinding!!.phoneNumberEt.text.toString())
//            val email = trimString(mBinding!!.emailEt.text.toString())
//            val username = trimString(mBinding!!.userNameEt.text.toString())
        }

        lifecycleScope.launch { mViewModel?.change() }
    }

    private fun trimString(text: String): String {
        var newText = text
        if (newText[0] == '"') {
            newText = newText.substring(1, newText.length - 1)
        }
        if (newText[newText.length - 1] == '"') {
            newText = newText.substring(0, newText.length - 2)
        }
        return newText
    }

    companion object {
        private const val TAG = "AccountFragment"
    }
}