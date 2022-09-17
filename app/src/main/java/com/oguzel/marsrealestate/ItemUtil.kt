package com.oguzel.marsrealestate

import android.icu.text.DecimalFormat
import android.icu.text.NumberFormat
import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

interface ItemUtil {
    /**
     * Bind Image function where we use Glide to show related image in ImageView
     * @param imgView ImageView that image will be shown
     * @param imgUrl  Image source url
     */
    fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(imgView.context)
                .load(imgUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .into(imgView)
        }
    }

    /**
     * formatPrice function formats price for user to observe it more clearly (thousand,million etc.)
     * @param price Item price will be given as input
     * @return  New formatted price
     */
    fun formatPrice(price: Int): String {
        val formatter: NumberFormat = DecimalFormat("#,###")
        return formatter.format(price)
    }
}
