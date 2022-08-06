package com.madudka.tarot.view

import androidx.fragment.app.Fragment

abstract class BaseFragment<T> : Fragment() {
    protected var listData : T? = null

    fun setData(data: T){
        listData = data
        if (isVisible) updateView()
    }

    abstract fun updateView()
}