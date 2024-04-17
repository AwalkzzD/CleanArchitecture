package com.example.cleanarchitecture.base.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    Picasso.get().load(url?.toUri()).fit().into(this)
}