package com.example.androidproject.utils

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.androidproject.MainActivity
import com.example.androidproject.R
import com.example.androidproject.fragments.MainFragment
import com.example.androidproject.interfaces.MainFragmentListener
import com.example.androidproject.model.Product
import com.example.androidproject.model.TimeLineOrderModel

class Navigator private constructor(context: Context) {

    private var mNavController: NavController? = null
    private var mMainFragmentListener : MainFragmentListener? = null
    private val mContext: Context = context

    fun replace() {
        val fragmentManager = (mContext as MainActivity).supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.base_nav_host_fragment, MainFragment())
            .addToBackStack(MainFragment::class.java.simpleName)
            .commit()
    }

    private fun createMainFragmentListenerBundle() : Bundle {
        val bundle = Bundle()
        bundle.putSerializable("KEY_MAIN_LISTENER", mMainFragmentListener)
        return bundle
    }

    fun setNavController(navController: NavController) {
        mNavController = navController
    }

    fun getNavController(): NavController? {
        return mNavController!!
    }

    fun showRegisterFragment() {
        mNavController?.navigate(R.id.action_loginFragment_to_registrationFragment, createMainFragmentListenerBundle())
    }

    fun showForgotPasswordFragment() {
        mNavController?.navigate(R.id.action_loginFragment_to_forgotpasswordFragment, createMainFragmentListenerBundle())
    }

    fun showTimelineFragment() {
        mNavController?.navigate(R.id.TimeLineFragment, createMainFragmentListenerBundle())
    }

    fun showAccountFragment() {
        mNavController?.navigate(R.id.timeline_toAccount, createMainFragmentListenerBundle())
    }

    fun showAccountFragment(username: String) {
        val bundle = Bundle();
        bundle.putString("KEY_USERNAME", username)
        bundle.putSerializable("KEY_MAIN_LISTENER", mMainFragmentListener)
        mNavController?.navigate(R.id.timeline_toAccount, bundle)
    }
//
    fun showMarketFragment() {
        mNavController?.navigate(R.id.marketFragment, createMainFragmentListenerBundle())
    }
//
    fun showCreateFareFragment() {
        mNavController?.navigate(R.id.createFareFragment, createMainFragmentListenerBundle())
    }

    fun setMainFragmentListener(mainFragmentListener: MainFragmentListener) {
        mMainFragmentListener = mainFragmentListener
    }

    fun goBack() {
        (mContext as Activity).onBackPressed()
    }

    fun showDetailFragment(item: Product, isOwner: Boolean) {
        val bundle = createMainFragmentListenerBundle()
        bundle.putString("KEY_PRODUCT_ID", item.product_id)
        bundle.putString("KEY_USERNAME", item.username)
        bundle.putString("KEY_UNITS", item.units)
        bundle.putString("KEY_TITLE", item.title)
        bundle.putString("KEY_PRICE", item.price_per_unit)
        bundle.putString("KEY_DESCRIPTION", item.description)
        bundle.putBoolean("KEY_ACTIVE", item.is_active)
        bundle.putBoolean("KEY_OWNER", isOwner)

        mNavController?.navigate(R.id.detailFragment, bundle)
    }

    fun showImageDialog(url: String) {
        val bundle = createMainFragmentListenerBundle()
        bundle.putString("KEY_URL", url)

        mNavController?.navigate(R.id.imageDialogFragment, bundle)
    }


    companion object {
        private var sInstance: Navigator? = null

        fun getsInstance(context: Context): Navigator {
            if (sInstance == null) {
                sInstance = Navigator(context)
            }
            return sInstance!!
        }
    }

}