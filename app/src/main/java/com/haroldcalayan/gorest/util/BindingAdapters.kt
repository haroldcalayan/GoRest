package com.haroldcalayan.gorest.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.haroldcalayan.gorest.R

@BindingAdapter("android:src")
fun setImageUrl(view: ImageView, url: String?) {
    Glide.with(view.context).load(url).placeholder(R.mipmap.ic_gorest).error(R.mipmap.ic_gorest).into(view)
}