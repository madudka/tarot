package com.madudka.tarot.viewmodel.layouts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.madudka.tarot.model.LayoutFullModel
import com.madudka.tarot.model.repository.LayoutRepository
import com.madudka.tarot.viewmodel.ViewModelExceptionHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LayoutsFullViewModel : ViewModelExceptionHandle() {
    private val layoutRepository = LayoutRepository()

    private val layoutFull: MutableLiveData<LayoutFullModel> by lazy { MutableLiveData<LayoutFullModel>() }

    fun getLayout(): LiveData<LayoutFullModel> = layoutFull

    fun loadLayout(id: Int){
        viewModelScope.launch(exceptionHandler) {
            //val fetchResult = fetchLayout(id)
            layoutFull.postValue(fetchLayout(id))
        }
    }

    private suspend fun fetchLayout(id: Int): LayoutFullModel{
        return withContext(Dispatchers.IO){
            layoutRepository.getLayout(id)
        }
    }
}