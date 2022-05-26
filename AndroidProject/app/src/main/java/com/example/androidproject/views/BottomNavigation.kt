package com.example.androidproject.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.androidproject.R
import com.example.androidproject.databinding.BottomNavigationBinding
import com.example.androidproject.enums.BottomNavEnum
import com.example.androidproject.utils.Navigator.Companion.getsInstance

class BottomNavigation(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val mBinding: BottomNavigationBinding =
        BottomNavigationBinding.inflate(LayoutInflater.from(context), this, true)

    @JvmOverloads
    constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : this(
        context,
        attrs,
        defStyleAttr,
        0
    ) {
    }

    private fun initViews() {
        mBinding.timeline.setOnClickListener { v: View? ->
            clearItemStates()
            setBottomNavItem(BottomNavEnum.TIMELINE)
            getsInstance(context).showTimelineFragment()
        }
        mBinding.market.setOnClickListener { v: View? ->
            clearItemStates()
            setBottomNavItem(BottomNavEnum.MARKET)
            getsInstance(context).showMarketFragment()
        }
        mBinding.fares.setOnClickListener { v: View? ->
            clearItemStates()
            setBottomNavItem(BottomNavEnum.FARES)
        }
    }

    fun setBottomNavItem(bottomNavItem: BottomNavEnum?) {
        clearItemStates()
        when (bottomNavItem) {
            BottomNavEnum.TIMELINE -> mBinding.timeline.background = ContextCompat.getDrawable(
                context, R.drawable.ic_timeline_selected
            )
            BottomNavEnum.MARKET -> mBinding.market.background =
                ContextCompat.getDrawable(context, R.drawable.ic_market_selected)
            BottomNavEnum.FARES -> mBinding.fares.background =
                ContextCompat.getDrawable(context, R.drawable.ic_fares_selected)
        }
    }

    fun clearItemStates() {
        mBinding.timeline.background = ContextCompat.getDrawable(context, R.drawable.ic_timeline)
        mBinding.market.background = ContextCompat.getDrawable(context, R.drawable.ic_market)
        mBinding.fares.background = ContextCompat.getDrawable(context, R.drawable.ic_fares)
    }

    init {
        initViews()
    }
}