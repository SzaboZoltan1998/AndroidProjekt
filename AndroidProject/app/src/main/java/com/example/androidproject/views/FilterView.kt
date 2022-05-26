package com.example.androidproject.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.androidproject.databinding.FilterViewBinding

class FilterView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val mBinding : FilterViewBinding = FilterViewBinding.inflate(
        LayoutInflater.from(context), this, true)

}