package com.madudka.tarot.viewmodel.divination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madudka.tarot.model.VerifyModel
import com.madudka.tarot.model.repository.VerifyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VerifyViewModel : ViewModel() {
    private val verifyRepository = VerifyRepository()

    private val verify: MutableLiveData<List<VerifyModel>> by lazy {
        MutableLiveData<List<VerifyModel>>()
    }

    fun getVerify(): LiveData<List<VerifyModel>> = verify

    init {
        viewModelScope.launch {
            val verifyFetchResult = fetchVerify(getIdArray(), 1)
            verify.postValue(verifyFetchResult)
        }
    }

//    fun loadVerify(idArray: Array<Int>, type: Int){
//        viewModelScope.launch {
//            val verifyFetchResult = fetchVerify(getIdArray(), 1)
//            verify.postValue(verifyFetchResult)
//        }
//    }

    private suspend fun fetchVerify(idArray: Array<Int>, type: Int): List<VerifyModel>{
        return withContext(Dispatchers.IO){
            verifyRepository.getVerifyFromDb(idArray, type)
        }
    }

    private fun getIdArray(): Array<Int> {
        return (1..10).map { (1..78).random() }.toTypedArray()
    }
}