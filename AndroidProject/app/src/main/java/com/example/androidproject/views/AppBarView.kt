package com.example.androidproject.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.example.androidproject.R
import com.example.androidproject.databinding.AppBarLayoutBinding
import com.example.androidproject.interfaces.MainFragmentListener
import com.example.androidproject.utils.Navigator

class AppBarView : RelativeLayout {

    private var mBinding: AppBarLayoutBinding? = null
    private var mMainFragmentListener: MainFragmentListener? = null
    private var isSearchEnabled = false
    private var isFilterEnabled = false

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        mBinding = AppBarLayoutBinding.inflate(LayoutInflater.from(context), this, true)
        initViews()
    }

    private fun initViews() {
        mBinding!!.searchButton.setOnClickListener {
            clearFilterSelectedState()
            if (!isSearchEnabled) {
                isSearchEnabled = true
                mBinding!!.searchButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_search_selected))
            } else {
                isSearchEnabled = false
                mBinding!!.searchButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_search))
            }

            mBinding!!.searchButton.requestLayout()
            mMainFragmentListener?.showSearchView()
        }

        mBinding!!.filterButton.setOnClickListener {
            clearSearchSelectedState()
            if (!isFilterEnabled) {
                isFilterEnabled = true
                mBinding!!.filterButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_filter_selected))
            } else {
                isFilterEnabled = false
                mBinding!!.filterButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_filter))
            }

            mMainFragmentListener?.showFilterView()
        }

        mBinding!!.accountButton.setOnClickListener {
            Navigator.getsInstance(context).showAccountFragment()
        }

        mBinding!!.mainButton.setOnClickListener {
            Navigator.getsInstance(context).goBack()
        }
    }

    private fun clearFilterSelectedState() {
        mBinding!!.filterButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_filter))
        isFilterEnabled = false
    }

    private fun clearSearchSelectedState() {
        mBinding!!.searchButton.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_search))
        isSearchEnabled = false
    }

    fun showBazarLogo() {
        mBinding!!.accountButton.visibility = GONE
        mBinding!!.filterButton.visibility = GONE
        mBinding!!.searchButton.visibility = GONE
        mBinding!!.mainButton.visibility = GONE
        mBinding!!.menuText.visibility = GONE
        mBinding!!.logo.visibility = VISIBLE
    }

    fun showHomeIcons() {
        mBinding!!.accountButton.visibility = VISIBLE
        mBinding!!.filterButton.visibility = VISIBLE
        mBinding!!.searchButton.visibility = VISIBLE
        mBinding!!.mainButton.visibility = GONE
        mBinding!!.menuText.visibility = GONE
        mBinding!!.logo.visibility = VISIBLE

        val layoutParams : MarginLayoutParams = mBinding!!.logo.layoutParams as MarginLayoutParams
        layoutParams.marginStart = context.resources.getDimensionPixelOffset(R.dimen.menu_text_margin_start)
        mBinding!!.logo.layoutParams = layoutParams

        val params : LayoutParams = mBinding!!.logo.layoutParams as LayoutParams
        params.removeRule(CENTER_IN_PARENT)
        params.addRule(CENTER_VERTICAL, TRUE)
        mBinding!!.logo.layoutParams = layoutParams

    }

    fun setMainFragmentListener(mainFragmentListener: MainFragmentListener) {
        mMainFragmentListener = mainFragmentListener;
    }

    fun showAccIcons() {
        clearSearchSelectedState()
        clearSearchSelectedState()
        mBinding!!.accountButton.visibility = VISIBLE
        mBinding!!.filterButton.visibility = GONE
        mBinding!!.searchButton.visibility = GONE
        mBinding!!.mainButton.visibility = VISIBLE
        mBinding!!.logo.visibility = GONE
        mBinding!!.menuText.visibility = VISIBLE
        mBinding!!.menuText.text = "Settings"

    }

    fun showMarketIcons() {
        clearFilterSelectedState()
        clearSearchSelectedState()
        mBinding!!.accountButton.visibility = VISIBLE
        mBinding!!.filterButton.visibility = GONE
        mBinding!!.searchButton.visibility = VISIBLE
        mBinding!!.mainButton.visibility = VISIBLE
        mBinding!!.logo.visibility = GONE
        mBinding!!.menuText.visibility = VISIBLE
        mBinding!!.menuText.text = "My Market"
    }

    fun showCreateFareIcons() {
        clearSearchSelectedState()
        clearFilterSelectedState()
        mBinding!!.accountButton.visibility = GONE
        mBinding!!.filterButton.visibility = GONE
        mBinding!!.searchButton.visibility = GONE
        mBinding!!.mainButton.visibility = VISIBLE
        mBinding!!.logo.visibility = GONE
        mBinding!!.menuText.visibility = VISIBLE
        mBinding!!.menuText.text = "Create your fare"
    }

    fun showLoginIcons() {
        clearSearchSelectedState()
        clearFilterSelectedState()
        mBinding!!.accountButton.visibility = GONE
        mBinding!!.filterButton.visibility = GONE
        mBinding!!.searchButton.visibility = GONE
        mBinding!!.mainButton.visibility = GONE
        mBinding!!.menuText.visibility = GONE

//        mBinding!!.logo.visibility = View.VISIBLE
//        val params : LayoutParams = mBinding!!.logo.layoutParams as LayoutParams
//        params.removeRule(CENTER_VERTICAL)
//        params.addRule(CENTER_IN_PARENT, TRUE)
//        mBinding!!.logo.layoutParams = layoutParams
    }

    fun showDetailIcons() {
        clearSearchSelectedState()
        clearFilterSelectedState()
        mBinding!!.accountButton.visibility = GONE
        mBinding!!.filterButton.visibility = GONE
        mBinding!!.searchButton.visibility = GONE
        mBinding!!.logo.visibility = GONE
        mBinding!!.mainButton.visibility = VISIBLE
        mBinding!!.menuText.visibility = VISIBLE
        mBinding!!.menuText.text = "Product detail"
    }
}