package com.oguzel.marsrealestate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oguzel.marsrealestate.data.MarsInfoItem
import com.oguzel.marsrealestate.databinding.ItemMarsBinding
import com.oguzel.marsrealestate.listener.IMarsPhotoClickListener

/**
 * Adapter for GridRecyclerView
 */
class GridRecyclerViewAdapter :
    RecyclerView.Adapter<GridRecyclerViewAdapter.GridRecyclerViewHolder>() {

    private var listener: IMarsPhotoClickListener? = null
    private val diffCallback = object : DiffUtil.ItemCallback<MarsInfoItem>() {
        override fun areItemsTheSame(oldItem: MarsInfoItem, newItem: MarsInfoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsInfoItem, newItem: MarsInfoItem): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var marsInfoList: List<MarsInfoItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    inner class GridRecyclerViewHolder(val binding: ItemMarsBinding) :
        RecyclerView.ViewHolder(binding.root), ItemUtil {

        /**
         * Function to bind item with data
         * @param item MarsInfoItem to get from api
         * @param listener listener to bind onClick method to item
         */
        fun bind(item: MarsInfoItem, listener: IMarsPhotoClickListener?) {
            bindImage(binding.marsImage, item.img_src)
            if (item.type == "buy") {
                binding.imageViewType.setImageResource(R.drawable.ic_sale)
            }
            (formatPrice(item.price) + "$").also { binding.textViewListPrice.text = it }
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
