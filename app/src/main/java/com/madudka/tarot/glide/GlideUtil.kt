package com.madudka.tarot.glide

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.madudka.tarot.R
import com.madudka.tarot.utils.getPathRefCard
import com.madudka.tarot.utils.toBitmap
import com.madudka.tarot.view.App.Companion.cardBack
import com.madudka.tarot.view.App.Companion.online
import com.madudka.tarot.view.App.Companion.settings

fun ImageView.loadImage(
    context: Context,
    id: Int = (1..78).random(),
    style: String = settings.cardStyle
) {
    GlideApp.with(context)
        .load(getPathRefCard(style, id))
        .placeholder(cardBack)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun ImageView.loadImage(context: Context, byteArray: ByteArray, id: Int = (1..78).random()) {
    if (online) {
        GlideApp.with(context)
            .load(getPathRefCard(settings.cardStyle, id))
            .placeholder(cardBack)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    } else this.setImageBitmap(byteArray.toBitmap())
}

fun loadCardBackImage(context: Context){
    Glide.with(context)
        .asDrawable()
        .load(getPathRefCard(settings.cardStyle, 0))
        .placeholder(R.drawable.test_back_card_img)
        .into(object : CustomTarget<Drawable>(){
            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable>?
            ) {
                cardBack = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }
        })
}