package com.oguzel.marsrealestate.data

/**
 * This data class holds Mars Item information what we get from "https://mars.udacity.com/realestate"
 * @property id Mars Item Ide
 * @property img_src Image Url
 * @property price Mars Item Price
 * @property type Mars Item Type (Buy or Rent)
 */
data class MarsInfoItem(
    val id: String,
    val img_src: String,
    val price: Int,
    val type: String
)