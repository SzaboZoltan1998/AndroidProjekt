package com.example.androidproject.views

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.androidproject.databinding.SearchViewBinding
import com.example.androidproject.interfaces.OnSearchListener

class SearchView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
    FrameLayout(context!!, attrs, defStyleAttr, defStyleRes), TextWatcher {

    private var listener:OnSearchListener? = null
    private var mBinding: SearchViewBinding =
        SearchViewBinding.inflate(LayoutInflater.from(context), this, true)

    @JvmOverloads
    constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    fun setListener(listener:OnSearchListener) {
        this.listener = listener
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        TODO("Not yet implemented")
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (s!!.length >= 3) {
            listener?.onSearch(s.toString())
        }
    }

    override fun afterTextChanged(s: Editable?) {
        TODO("Not yet implemented")
    }


}