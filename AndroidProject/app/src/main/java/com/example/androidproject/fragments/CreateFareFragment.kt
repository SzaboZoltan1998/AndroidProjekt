package com.example.androidproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.androidproject.databinding.CreateFareFragmentBinding
import com.example.androidproject.interfaces.MainFragmentListener
import com.example.androidproject.model.AddProductModel
import com.example.androidproject.repository.Repository
import com.example.androidproject.utils.Navigator
import com.example.androidproject.viewmodels.CreateFareViewModel
import com.example.androidproject.viewmodels.CreateFareViewModelFactory
import kotlinx.coroutines.launch

class CreateFareFragment(): Fragment() {

    private val mMainFragmentListener: MainFragmentListener? = null
    private var mBinding: CreateFareFragmentBinding? = null
    private var mViewModel: CreateFareViewModel? = null

    companion object {
        private const val TAG = "CreateFareFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = CreateFareViewModelFactory(requireContext(), Repository())
        mViewModel = ViewModelProvider(this, factory).get(CreateFareViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (mBinding == null) {
            mBinding = CreateFareFragmentBinding.inflate(inflater, container, false)
            initViews()
        }

        mMainFragmentListener?.showCreateFareIcons()
        mMainFragmentListener?.hideBottomNav()
        return mBinding!!.root
    }

    private fun initViews() {
        mMainFragmentListener?.showCreateFareIcons()

        mBinding!!.createFareButton.setOnClickListener {
            val model = AddProductModel(
                mBinding!!.titleEt.text.toString(),
                mBinding!!.descriptionEt.text.toString(),
                mBinding!!.priceEt.text.toString(),
                mBinding!!.amountEt.text.toString(),
                mBinding!!.statSwitch.isChecked,
                5.0,
                "piece",
                "RON"
            )

            lifecycleScope.launch {
                mViewModel?.addProduct(model)
            }

            mViewModel!!.responseModel.observe(viewLifecycleOwner) { model ->
                run {
                    Navigator.getsInstance(requireContext()).showMarketFragment()
                }
            }
        }
    }


}
