package com.madudka.tarot.viewmodel.layouts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.madudka.tarot.model.LayoutModel
import com.madudka.tarot.model.repository.LayoutRepository
import com.madudka.tarot.viewmodel.ViewModelExceptionHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LayoutsViewModel : ViewModelExceptionHandle() {
    private val repository = LayoutRepository()

    private val layouts: MutableLiveData<List<LayoutModel>> by lazy{
        MutableLiveData<List<LayoutModel>>()
    }

    fun getLayouts() : LiveData<List<LayoutModel>> = layouts

    init {
        viewModelScope.launch(exceptionHandler) {
            val layoutsFetchResult = fetchLayouts()
            layouts.postValue(layoutsFetchResult)
        }
    }

    private suspend fun fetchLayouts(): List<LayoutModel> {
        return withContext(Dispatchers.IO){
            repository.getAllLayouts()
        }
    }

}