package com.example.androidproject.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.androidproject.databinding.SearchViewBinding

class SearchView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
    FrameLayout(context!!, attrs, defStyleAttr, defStyleRes) {

    private var mBinding: SearchViewBinding =
        SearchViewBinding.inflate(LayoutInflater.from(context), this, true)

    @JvmOverloads
    constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )
}