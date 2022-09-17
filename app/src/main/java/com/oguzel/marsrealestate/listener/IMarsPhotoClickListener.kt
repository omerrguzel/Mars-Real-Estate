package com.oguzel.marsrealestate.listener

import com.oguzel.marsrealestate.data.MarsInfoItem

/**
 * This interface is being used in RecyclerViewAdapter
 */
interface IMarsPhotoClickListener {

    fun onClick(name : MarsInfoItem)
}