package com.example.androidproject.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidproject.R
import com.example.androidproject.adapters.TimeLineRecyclerViewAdapter
import com.example.androidproject.databinding.TimelineFragmentBinding
import com.example.androidproject.enums.BottomNavEnum
import com.example.androidproject.interfaces.MainFragmentListener
import com.example.androidproject.model.Product
import com.example.androidproject.repository.Repository
import com.example.androidproject.viewmodels.RegistrationViewModel
import com.example.androidproject.viewmodels.RegistrationViewModelFactory
import com.example.androidproject.viewmodels.TimelineFactory
import com.example.androidproject.viewmodels.TimelineViewModel
import kotlinx.coroutines.launch

class TimeLineFragment() : Fragment() {
    private var mMainFragmentListener: MainFragmentListener? = null
    private var mBinding: TimelineFragmentBinding? = null
    private var mViewModel: TimelineViewModel? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (arguments != null) {
            mMainFragmentListener = arguments?.getSerializable("KEY_MAIN_LISTENER") as MainFragmentListener?
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = TimelineFactory(requireContext(), Repository())
        mViewModel = ViewModelProvider(this, factory).get(TimelineViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (mBinding == null) {
            mBinding = TimelineFragmentBinding.inflate(inflater, container, false)
            initViews()
        }
        mMainFragmentListener?.showHomeIcons()
        mMainFragmentListener?.showBottomNav()
        mMainFragmentListener?.setBottomNavItem(BottomNavEnum.TIMELINE)
        return mBinding!!.root
    }

    private fun initViews() {
        mMainFragmentListener?.showBottomNav()
        mMainFragmentListener?.showHomeIcons()
        mMainFragmentListener?.setBottomNavItem(BottomNavEnum.TIMELINE)
        mBinding!!.timelineRv.setHasFixedSize(true)
        mBinding!!.timelineRv.layoutManager = LinearLayoutManager(context)
        val itemDecoration =
            DividerItemDecoration(mBinding!!.root.context, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(
            ContextCompat.getDrawable(
                mBinding!!.root.context,
                R.drawable.divider
            )!!
        )
        mBinding!!.timelineRv.addItemDecoration(itemDecoration)

        lifecycleScope.launch { mViewModel?.getProducts() }

        mViewModel!!.responseData.observe(viewLifecycleOwner){ model ->
            run {
                val adapter = TimeLineRecyclerViewAdapter(model.products.toMutableList(), false)
                mBinding!!.timelineRv.adapter = adapter
            }

        }
    }
}