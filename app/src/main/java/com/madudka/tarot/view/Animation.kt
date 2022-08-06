package com.madudka.tarot.view

import android.animation.*
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart

fun View.customCenterYRotate(
    start: Float,
    end: Float,
    dur: Long = 1000,
    del: Long = 0,
    startFun: () -> Unit = {}){
    ObjectAnimator.ofFloat(this, View.ROTATION_Y, start, end).apply {
        duration = dur
        startDelay = del
        interpolator = LinearInterpolator()
        doOnStart{ startFun() }
        disableViewDuringAnimation(this@customCenterYRotate)
    }.start()
}

fun View.customScaleOutWithMove(
    dur: Long = 1000,
    del: Long = 0,
    endFun: () -> Unit = {}){
    val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1F, 0.63F)
    val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1F, 0.63F)
    val scale = ObjectAnimator.ofPropertyValuesHolder(this, scaleX, scaleY).apply {
        //duration = dur
        //startDelay = del
        disableViewDuringAnimation(this@customScaleOutWithMove)
    }

    val moveX = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, -310F)
    val moveY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, -260F)
    val move = ObjectAnimator.ofPropertyValuesHolder(this, moveX, moveY).apply {
        //duration = dur
        //startDelay = del
    }

    AnimatorSet().apply {
        playTogether(scale, move)
        duration = dur
        startDelay = del
        doOnEnd { endFun() }
    }.start()
}

fun View.customScalePulseWithMove(
    dur: Long = 1000,
    del: Long = 0){
    val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1F, 2.2F, 1F)
    val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1F, 2.2F, 1F)
    val scale = ObjectAnimator.ofPropertyValuesHolder(this, scaleX, scaleY).apply {
        //duration = dur
        //startDelay = del
        disableViewDuringAnimation(this@customScalePulseWithMove)
    }

    val moveX = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 0F)
    val moveY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0F)
    val move = ObjectAnimator.ofPropertyValuesHolder(this, moveX, moveY).apply {
        //duration = dur
        //startDelay = del
    }

    AnimatorSet().apply {
        playTogether(scale, move)
        duration = dur
        startDelay = del
    }.start()
}

private fun ObjectAnimator.disableViewDuringAnimation(view: View) {
    addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            view.isEnabled = false
        }

        override fun onAnimationEnd(animation: Animator?) {
            view.isEnabled = true
        }
    })
}
