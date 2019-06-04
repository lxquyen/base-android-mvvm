package com.lxquyen.sample.common.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lxquyen.sample.R

fun ImageView.loadImage(url: String?){
    val option = RequestOptions.placeholderOf(R.mipmap.ic_launcher)
    Glide.with(this)
        .load(url)
        .apply(option)
        .into(this)
}