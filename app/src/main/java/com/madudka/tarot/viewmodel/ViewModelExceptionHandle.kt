package com.madudka.tarot.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

open class ViewModelExceptionHandle : ViewModel() {
    val error: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun getError(): LiveData<String> = error

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        error.postValue("Exception handled: ${throwable.localizedMessage}")
    }
}