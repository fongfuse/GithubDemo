package com.fongfuse.githubdemo.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.setImageUrl(imgResource : Any?) {
    Glide.with(context)
        .load(imgResource)
//        .circleCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}