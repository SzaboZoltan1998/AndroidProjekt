package com.example.androidproject.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidproject.R
import com.example.androidproject.adapters.ImageRecyclerView
import com.example.androidproject.databinding.DetailFragmentBinding
import com.example.androidproject.interfaces.MainFragmentListener
import com.example.androidproject.utils.ImageModel
import com.example.androidproject.utils.DbHelper
import com.example.androidproject.utils.Navigator

class DetailFragment : Fragment() {

    private var mMainFragmentListener: MainFragmentListener? = null
    private var mBinding: DetailFragmentBinding? = null
    private var mUsername: String? = null
    private var mProductId: String? = null
    private var mUnits: String? = null
    private var mTitle: String? = null
    private var mPrice: String? = null
    private var mDecription: String? = null
    private var mActive: Boolean = true
    private var isOwner: Boolean = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (arguments != null) {
            mMainFragmentListener = arguments?.getSerializable("KEY_MAIN_LISTENER") as MainFragmentListener?
            mProductId = arguments?.getString("KEY_PRODUCT_ID")
            mTitle = arguments?.getString("KEY_USERNAME")
            mUnits = arguments?.getString("KEY_UNITS")
            mTitle = arguments?.getString("KEY_TITLE")
            mPrice = arguments?.getString("KEY_PRICE")
            mDecription = arguments?.getString("KEY_DESCRIPTION")
            mActive = arguments?.getBoolean("KEY_ACTIVE")!!
            isOwner = arguments?.getBoolean("KEY_OWNER")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mBinding == null) {
            mBinding = DetailFragmentBinding.inflate(inflater, container, false)
            initViews()
        }

        return mBinding?.root
    }

    private fun initViews() {
        mMainFragmentListener?.showDetailFragmentIcons()
        mMainFragmentListener?.hideBottomNav()

        if (isOwner) {
            mBinding!!.editButton.visibility = View.VISIBLE
            mBinding!!.editButton.setOnClickListener {
                Navigator.getsInstance(requireContext()).showCreateFareFragment()
            }

            mBinding!!.totalItems.visibility = View.VISIBLE
            mBinding!!.totalItems.text = mUnits
            mBinding!!.priceItem.visibility = View.VISIBLE
            mBinding!!.priceItem.text = mPrice
            mBinding!!.selledItems.visibility = View.VISIBLE
            mBinding!!.selledItems.text = "0"
            mBinding!!.reveniew.visibility = View.VISIBLE
            mBinding!!.reveniew.text = "850 RON"
        }

        mBinding!!.statusIcon.visibility = View.VISIBLE
        mBinding!!.statusText.visibility = View.VISIBLE

        if(mActive) {
            mBinding!!.statusText.text = "Active"
            mBinding!!.statusText.setTextColor(ContextCompat.getColor(requireContext(), R.color.turqoise))
            mBinding!!.statusIcon.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_active))
        } else {
            mBinding!!.statusText.text = "Inactive"
            mBinding!!.statusText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            mBinding!!.statusIcon.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_inactive))
        }

        mBinding!!.title.text = mTitle
        mBinding!!.price.text = mPrice
        mBinding!!.description.text = mDecription
        mBinding!!.accName.text = mUsername

        mBinding!!.imageRv.setHasFixedSize(true)
        mBinding!!.imageRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val images = getImagesFromDb()

        val itemDecoration =
            DividerItemDecoration(mBinding!!.root.context, DividerItemDecoration.HORIZONTAL)
        itemDecoration.setDrawable(
            ContextCompat.getDrawable(
                mBinding!!.root.context,
                R.drawable.divider_vertical
            )!!
        )

        mBinding!!.imageRv.addItemDecoration(itemDecoration)
        mBinding!!.imageRv.adapter = ImageRecyclerView(images)

    }

    private fun getImagesFromDb() : ArrayList<ImageModel> {
        val dbHelper = DbHelper.getInstance(requireContext())
        return dbHelper!!.data
    }
}