package com.example.androidproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.databinding.ImageItemBinding
import com.example.androidproject.utils.ImageModel
import com.example.androidproject.utils.Navigator
import com.squareup.picasso.Picasso

class ImageRecyclerView(private var images: ArrayList<ImageModel>) :
    RecyclerView.Adapter<ImageRecyclerView.ViewHolder>() {

    private var mContext: Context? = null;

    class ViewHolder (val mBinding: ImageItemBinding) : RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        return ViewHolder(ImageItemBinding.inflate(LayoutInflater.from(mContext)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = images[position]
        Picasso.get()
            .load(model.url)
            .into(holder.mBinding.imageItem)

        holder.mBinding.imageItem.setOnClickListener {
            Navigator.getsInstance(mContext!!).showImageDialog(model.url)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}