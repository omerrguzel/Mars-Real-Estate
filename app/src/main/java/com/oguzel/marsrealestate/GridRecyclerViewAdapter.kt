package com.oguzel.marsrealestate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.oguzel.marsrealestate.data.MarsInfoItem
import com.oguzel.marsrealestate.databinding.ItemMarsBinding
import com.oguzel.marsrealestate.listener.IMarsPhotoClickListener


class GridRecyclerViewAdapter :
    RecyclerView.Adapter<GridRecyclerViewAdapter.GridRecyclerViewHolder>() {

//    private lateinit var marsInfoList : List<MarsInfoItem>
//    private var marsInfoList?: List<MarsInfoItem> = []
    private var listener: IMarsPhotoClickListener? = null
    private val diffCallback = object : DiffUtil.ItemCallback<MarsInfoItem>(){
        override fun areItemsTheSame(oldItem: MarsInfoItem, newItem: MarsInfoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsInfoItem, newItem: MarsInfoItem): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)
        var marsInfoList : List<MarsInfoItem>
            get() = differ.currentList
            set(value) {differ.submitList(value)}

    inner class GridRecyclerViewHolder(val binding: ItemMarsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MarsInfoItem, listener: IMarsPhotoClickListener?) {
            Glide.with(binding.marsImage.context)
                .load(item.img_src)
                .apply(RequestOptions().override(200, 200))
                .into(binding.marsImage)
            binding.marsImage.setOnClickListener { listener?.onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = ItemMarsBinding.inflate(layoutInflater, parent, false)
        return GridRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridRecyclerViewHolder, position: Int) {
        marsInfoList[position]?.let {
            holder.bind(it, listener)
        }
    }

    override fun getItemCount(): Int {
        return marsInfoList!!.size
    }

    fun setData(newList: List<MarsInfoItem>?) {
        if (newList != null) {
            marsInfoList = newList
        }
        notifyDataSetChanged()
    }

    fun setMarsPhotoOnClickListener(listener: IMarsPhotoClickListener) {
        this.listener = listener
    }
}