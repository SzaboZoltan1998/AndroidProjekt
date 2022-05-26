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
import com.example.androidproject.interfaces.MainFragmentListener
import com.example.androidproject.interfaces.OnItemClickListener
import com.example.androidproject.model.AddProductModel
import com.example.androidproject.model.Product
import com.example.androidproject.repository.Repository
import com.example.androidproject.utils.Navigator
import com.example.androidproject.viewmodels.TimelineFactory
import com.example.androidproject.viewmodels.TimelineViewModel
import kotlinx.coroutines.launch

class MarketFragment : Fragment(), OnItemClickListener {

    private var mMainFragmentListener: MainFragmentListener? = null
    private var mBinding: TimelineFragmentBinding? = null
    private var mViewModel: TimelineViewModel? = null
    private var adapter: TimeLineRecyclerViewAdapter? = null


    companion object {
        private const val TAG = "MarketFragment"
    }

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
    ): View? {
        if (mBinding == null) {
            mBinding = TimelineFragmentBinding.inflate(inflater, container, false)
            initViews()
        }

        mMainFragmentListener?.showMarketIcons()
        mMainFragmentListener?.showBottomNav()
        return mBinding?.root
    }

    private fun initViews() {


        mBinding!!.addProduct.visibility = View.VISIBLE

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

        mBinding!!.addProduct.setOnClickListener {
            Navigator.getsInstance(requireContext()).showCreateFareFragment()
        }

        lifecycleScope.launch { mViewModel?.getMyProducts() }

        mViewModel!!.myProducts.observe(viewLifecycleOwner) { model ->
            run {
                adapter = TimeLineRecyclerViewAdapter(model.products.toMutableList(), true, this)
                mBinding!!.timelineRv.adapter = adapter
            }
        }
    }

    override fun onItemLongClick(model: AddProductModel) {
        mBinding!!.addProduct.visibility = View.GONE
        mBinding!!.deleteProduct.visibility = View.VISIBLE
        mBinding!!.deleteProduct.setOnClickListener {
            for (item in adapter!!.orders) {
                if (item == model) {
                    adapter!!.orders.remove(item)
                    adapter!!.notifyDataSetChanged()
                    break
                }
            }

            if (!adapter!!.orders.isEmpty()) {
                mBinding!!.addProduct.visibility = View.VISIBLE
                mBinding!!.deleteProduct.visibility = View.GONE
                return@setOnClickListener
            }

            mBinding!!.deleteProduct.visibility = View.GONE

        }
    }

    override fun onItemLongClick(model: Product) {
        mBinding!!.addProduct.visibility = View.GONE
        mBinding!!.deleteProduct.visibility = View.VISIBLE
        mBinding!!.deleteProduct.setOnClickListener {
            for (item in adapter!!.orders) {
                if (item == model) {
                    adapter!!.orders.remove(item)
                    adapter!!.notifyDataSetChanged()
                    break
                }
            }

            if (!adapter!!.orders.isEmpty()) {
                mBinding!!.addProduct.visibility = View.VISIBLE
                mBinding!!.deleteProduct.visibility = View.GONE
                return@setOnClickListener
            }

            mBinding!!.deleteProduct.visibility = View.GONE

        }
    }


}
