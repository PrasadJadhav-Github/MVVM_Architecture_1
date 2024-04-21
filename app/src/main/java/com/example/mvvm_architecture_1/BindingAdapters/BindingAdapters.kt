package com.example.mvvm_architecture_1.BindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image_uel")
fun setWebImageUrlToImageView(
    imageView: ImageView,
    imageUrl :String?
){
    if (imageUrl == null){
        return
    }

    Glide.with(imageView)
        .load(imageUrl)
        .into(imageView)
}