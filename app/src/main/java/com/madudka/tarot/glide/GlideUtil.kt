package com.madudka.tarot.glide

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.firebase.storage.StorageReference
import com.madudka.tarot.R

fun loadImage(context: Context, pathRef: StorageReference, imageView: ImageView){
    GlideApp.with(context)
        .load(pathRef)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .error(R.drawable.test_back_card_img)
        .placeholder(R.drawable.test_back_card_img)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}