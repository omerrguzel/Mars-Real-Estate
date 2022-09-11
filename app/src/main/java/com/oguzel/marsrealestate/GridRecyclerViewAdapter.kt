package com.oguzel.marsrealestate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oguzel.marsrealestate.data.MarsInfoItem
import com.oguzel.marsrealestate.data.MarsInfoList
import com.oguzel.marsrealestate.databinding.ItemMarsBinding
import com.oguzel.marsrealestate.listener.IMarsPhotoClickListener

class GridRecyclerViewAdapter :
    RecyclerView.Adapter<GridRecyclerViewAdapter.GridRecyclerViewHolder>() {

    private lateinit var marsInfoList: MarsInfoList
    private var listener: IMarsPhotoClickListener? = null


    inner class GridRecyclerViewHolder(val binding: ItemMarsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MarsInfoItem, listener: IMarsPhotoClickListener?) {
            Glide.with(binding.marsImage.context).load(item.img_src).into(binding.marsImage)
            binding.marsImage.setOnClickListener { listener?.onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = ItemMarsBinding.inflate(layoutInflater, parent, false)
        return GridRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridRecyclerViewHolder, position: Int) {
        marsInfoList[position].let {
            holder.bind(it, listener)
        }
    }

    override fun getItemCount(): Int {
        return marsInfoList.size
    }

    fun setData(newList: MarsInfoList?) {
        if (newList != null) {
            marsInfoList = newList
        }
        notifyDataSetChanged()
    }

    fun setRestaurantOnClickListener(listener: IMarsPhotoClickListener) {
        this.listener = listener
    }
}