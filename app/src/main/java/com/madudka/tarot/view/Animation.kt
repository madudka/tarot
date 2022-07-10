package com.madudka.tarot.view

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.LinearInterpolator
import android.view.animation.ScaleAnimation
import androidx.core.animation.doOnStart

fun View.customCenterYRotate(
    start: Float,
    end: Float,
    dur: Long = 1000,
    delay: Long = 0,
    addFun: () -> Unit = {}){
    ObjectAnimator.ofFloat(this, View.ROTATION_Y, start, end).apply {
        duration = dur
        startDelay = delay
        interpolator = LinearInterpolator()
        doOnStart{ addFun() }
    }.start()
}

fun View.customScaleSmall(view: View){
    val scaleXAnimator = ObjectAnimator.ofFloat(view,"scaleX",1F,3F,1F)
    scaleXAnimator.duration = 1000
    scaleXAnimator.start()
}
