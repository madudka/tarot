package com.madudka.tarot.view

import android.animation.*
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.opengl.Visibility
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.ImageView
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
    val moveY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, -400F)
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

fun View.fadeHide() {
    this.animate()
        .alpha(0F)
        .setDuration(resources.getInteger(android.R.integer.config_mediumAnimTime).toLong())
        .setListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                this@fadeHide.visibility = View.GONE
            }
        })
}

fun View.fadeShow() {
    this.animate()
        .alpha(1F)
        .setDuration(resources.getInteger(android.R.integer.config_shortAnimTime).toLong())
        .setListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                this@fadeShow.visibility = View.VISIBLE
            }
        })
}

fun zoomImage(
    smallView: View,
    bigView: View,
    bigViewImage: ImageView,
    bitmap: Bitmap,
    currentAnimator: Animator?,
    shortAnimationDuration: Int) {

    var currentAnimator: Animator? = currentAnimator

    //Отмена анимации, если она уже запущена
    currentAnimator?.cancel()

    //Загружаем изображение большого формата
    bigViewImage.setImageBitmap(bitmap)

    //Вычисляем начальные и конечные границы увеличенного изображения
    val startBoundsInt = Rect()
    val finalBoundsInt = Rect()
    val globalOffset = Point()

    //Начальные границы - видимый прямоугольник миниатюры,
    // а конечные границы - видимый прямоугольник большого изображения.
    // Также установливаем смещение большого изображения в качестве начала координат для границ,
    // поскольку это начало координат для свойств анимации (X, Y).
    smallView.getGlobalVisibleRect(startBoundsInt)
    bigView.getGlobalVisibleRect(finalBoundsInt, globalOffset)
    startBoundsInt.offset(-globalOffset.x, -globalOffset.y)
    finalBoundsInt.offset(-globalOffset.x, -globalOffset.y)

    val startBounds = RectF(startBoundsInt)
    val finalBounds = RectF(finalBoundsInt)

    //Отрегулируйте начальные границы так, чтобы они имели то же соотношение сторон,
    // что и конечные границы, используя технику "обрезки по центру".
    // Это предотвращает нежелательное растяжение во время анимации.
    // Также рассчитайте начальный коэффициент масштабирования (конечный коэффициент масштабирования всегда равен 1,0).
    val startScale: Float
    if ((finalBounds.width() / finalBounds.height() > startBounds.width() / startBounds.height())) {
        //Расширяем начальные границы по-горизонтали
        startScale = startBounds.height() / finalBounds.height()
        val startWidth: Float = startScale * finalBounds.width()
        val deltaWidth: Float = (startWidth - startBounds.width()) / 2
        startBounds.left -= deltaWidth.toInt()
        startBounds.right += deltaWidth.toInt()
    } else {
        //Расширяем начальные границы по-вертикали
        startScale = startBounds.width() / finalBounds.width()
        val startHeight: Float = startScale * finalBounds.height()
        val deltaHeight: Float = (startHeight - startBounds.height()) / 2f
        startBounds.top -= deltaHeight.toInt()
        startBounds.bottom += deltaHeight.toInt()
    }

    //Скрываем small и показываем big.
    //Когда начнется анимация, big будет помещен на место small.
    smallView.alpha = 0f
    bigView.visibility = View.VISIBLE

    //Установите точку поворота для преобразований SCALE_X и SCALE_Y в верхнем левом углу big
    // (по умолчанию используется центр вида).
    bigView.pivotX = 0f
    bigView.pivotY = 0f

    //Создаем и запускаем параллельную анимацию четырех свойств перехода и масштаба (X, Y, SCALE_X и SCALE_Y).
    currentAnimator = AnimatorSet().apply {
        play(ObjectAnimator.ofFloat(
            bigView,
            View.X,
            startBounds.left,
            finalBounds.left)
        ).apply {
            with(ObjectAnimator.ofFloat(bigView, View.Y, startBounds.top, finalBounds.top))
            with(ObjectAnimator.ofFloat(bigView, View.SCALE_X, startScale, 1f))
            with(ObjectAnimator.ofFloat(bigView, View.SCALE_Y, startScale, 1f))
        }
        duration = shortAnimationDuration.toLong()
        interpolator = DecelerateInterpolator()
        addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator) {
                currentAnimator = null
            }

            override fun onAnimationCancel(animation: Animator) {
                currentAnimator = null
            }
        })
        start()
    }

    //При нажатии на big возвращаемся к исходным границам и показываем small.
    bigViewImage.setOnClickListener {
        currentAnimator?.cancel()

        // Animate the four positioning/sizing properties in parallel,
        // back to their original values.
        currentAnimator = AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(bigView, View.X, startBounds.left)).apply {
                with(ObjectAnimator.ofFloat(bigView, View.Y, startBounds.top))
                with(ObjectAnimator.ofFloat(bigView, View.SCALE_X, startScale))
                with(ObjectAnimator.ofFloat(bigView, View.SCALE_Y, startScale))
            }
            duration = shortAnimationDuration.toLong()
            interpolator = DecelerateInterpolator()
            addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationEnd(animation: Animator) {
                    smallView.alpha = 1f
                    bigView.visibility = View.GONE
                    currentAnimator = null
                }

                override fun onAnimationCancel(animation: Animator) {
                    smallView.alpha = 1f
                    bigView.visibility = View.GONE
                    currentAnimator = null
                }
            })
            start()
        }
    }
}