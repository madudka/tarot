package com.madudka.tarot.utils

import android.app.Activity
import android.view.Gravity
import android.view.View
import com.madudka.tarot.R
import me.toptas.fancyshowcase.FancyShowCaseQueue
import me.toptas.fancyshowcase.FancyShowCaseView
import me.toptas.fancyshowcase.FocusShape

fun showCases(activity: Activity, casesInfoList: List<Triple<View, String, String>>){
    if (casesInfoList.isNotEmpty()) {
        val mQueue = FancyShowCaseQueue()

        casesInfoList.forEachIndexed { index, case ->
            mQueue.add(
                buildShowCase(activity, case.first, case.second, case.third, (index != 0))
            )
        }

        mQueue.show()
    }
}

fun showCase(activity: Activity, view: View, description: String, key: String){
    buildShowCase(activity, view, description, key).show()
}

private fun buildShowCase(activity: Activity, view: View, description: String, key: String, fsw: Boolean = true) =
    FancyShowCaseView.Builder(activity)
        .backgroundColor(R.color.dark_purple)
        .focusBorderColor(R.color.peach)
        .focusShape(FocusShape.ROUNDED_RECTANGLE)
        .titleStyle(R.style.Custom_ShowCaseTitle, Gravity.CENTER or Gravity.TOP)
        .fitSystemWindows(fsw)
        .focusOn(view)
        .title(description)
        //TODO поставить showonce перед публикацией
        //.showOnce(key)
        .build()