package com.example.androidproject.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.databinding.OrderLayoutBinding
import com.example.androidproject.model.AddProductModel
import com.example.androidproject.model.TimeLineOrderModel
import com.example.androidproject.utils.Navigator
import com.example.androidproject.utils.SharedPrefUtils
import com.example.androidproject.interfaces.OnItemClickListener
import com.example.androidproject.model.Product

class TimeLineRecyclerViewAdapter : RecyclerView.Adapter<TimeLineRecyclerViewAdapter.ViewHolder> {
    public var orders: MutableList<Any> = ArrayList()
    private var mIsMarketActivated = false
    private var mOnItemClickListener: OnItemClickListener? = null
    private var mContext: Context? = null

    constructor(orders: MutableList<Any>, isMarketActivated: Boolean) {
        this.orders = orders
        mIsMarketActivated = isMarketActivated
    }

    constructor(orders: MutableList<Any> , isMarketActivated: Boolean, listener: OnItemClickListener?) {
        this.orders = orders
        mIsMarketActivated = isMarketActivated
        mOnItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val mBinding = OrderLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = orders[position]

        holder.mBinding.productItem.setOnClickListener {
            val username = (item as Product).username
            Navigator.getsInstance(mContext!!).showDetailFragment(item, username == item.username)
        }

        if (item is Product) {
            Log.d("3SS", "onBindViewHolder: "+item.product_id)
            holder.mBinding.orderButton.visibility = View.VISIBLE
            holder.mBinding.accName.text = item.username
            holder.mBinding.productPrice.text =
                item.price_per_unit + " " + item.price_type + "/" + item.amount_type
            holder.mBinding.title.text = item.title
            holder.itemView.setOnLongClickListener { v: View? ->
                holder.itemView.isSelected = true
                if (mOnItemClickListener != null) {
                    mOnItemClickListener!!.onItemLongClick(item)
                }
                true
            }
            if (mIsMarketActivated) {
                holder.mBinding.orderButton.visibility = View.GONE
                if (item.is_active) {
                    showActiveStatusGroupVisibility(holder)
                } else {
                    showInactiveStatusGroupVisibility(holder)
                }
            }

            holder.mBinding.accName.setOnClickListener {
                Navigator.getsInstance(context = mContext!!).showAccountFragment(item.username!!)
            }
        } else if (item is AddProductModel) {
            holder.mBinding.orderButton.visibility = View.VISIBLE
            holder.mBinding.accName.text =
                SharedPrefUtils.readString(holder.mBinding.root.context, SharedPrefUtils.USERNAME)
            holder.mBinding.productPrice.text =
                item.price_per_unit + " " + item.price_type + "/" + item.amount_type
            holder.mBinding.title.text = item.title
            holder.itemView.setOnLongClickListener { v: View? ->
                holder.itemView.isSelected = true
                if (mOnItemClickListener != null) {
                    mOnItemClickListener!!.onItemLongClick(item)
                }
                true
            }
            if (mIsMarketActivated) {
                holder.mBinding.orderButton.visibility = View.GONE
                if (item.is_active) {
                    showActiveStatusGroupVisibility(holder)
                } else {
                    showInactiveStatusGroupVisibility(holder)
                }
            }
        }


    }

    private fun showActiveStatusGroupVisibility(holder: ViewHolder) {
        val visibility = holder.mBinding.activeStatusIcon.visibility
        if (visibility != View.VISIBLE) {
            holder.mBinding.inactiveStatusIcon.visibility = View.GONE
            holder.mBinding.inactiveStatusText.visibility = View.GONE
            holder.mBinding.activeStatusIcon.visibility = View.VISIBLE
            holder.mBinding.activeStatusText.visibility = View.VISIBLE
        }
    }

    private fun showInactiveStatusGroupVisibility(holder: ViewHolder) {
        val visibility = holder.mBinding.inactiveStatusIcon.visibility
        if (visibility != View.VISIBLE) {
            holder.mBinding.activeStatusIcon.visibility = View.GONE
            holder.mBinding.activeStatusText.visibility = View.GONE
            holder.mBinding.inactiveStatusIcon.visibility = View.VISIBLE
            holder.mBinding.inactiveStatusIcon.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    class ViewHolder(val mBinding: OrderLayoutBinding) : RecyclerView.ViewHolder(
        mBinding.root
    )
}