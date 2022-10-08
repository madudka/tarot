package com.madudka.tarot.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.firebase.storage.StorageReference

fun loadImage(context: Context, pathRef: StorageReference, imagerView: ImageView){
    GlideApp.with(context)
        .asBitmap()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .load(pathRef)
        .into(object : CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                imagerView.setImageBitmap(resource)
            }
            override fun onLoadCleared(placeholder: Drawable?) {
                // this is called when imageView is cleared on lifecycle call or for
                // some other reason.
                // if you are referencing the bitmap somewhere else too other than this imageView
                // clear it here as you can no longer have the bitmap
            }
        })
}