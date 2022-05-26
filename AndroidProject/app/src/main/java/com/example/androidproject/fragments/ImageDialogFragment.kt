package com.example.androidproject.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.androidproject.R
import com.example.androidproject.databinding.ImageBigFragmentBinding

class ImageDialogFragment : DialogFragment() {
    private var mBinding: ImageBigFragmentBinding? = null
    private var mUrl: String? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (arguments != null) {
            mUrl = arguments?.getString("KEY_URL")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (mBinding == null) {
            mBinding = ImageBigFragmentBinding.inflate(inflater, container, false)
            initViews()
        }
        return mBinding!!.root
    }

    private fun initViews() {
        if (mUrl != null) {
            Glide.with(requireContext())
                .load(mUrl)
                .into(mBinding!!.imageContainer)
        } else {
            Glide.with(requireContext())
                .load(R.drawable.ic_inactive)
                .into(mBinding!!.imageContainer)
        }
    }
}}