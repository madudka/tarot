package com.madudka.tarot.viewmodel.divination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.madudka.tarot.model.VerifyModel
import com.madudka.tarot.model.repository.VerifyRepository
import com.madudka.tarot.viewmodel.ViewModelExceptionHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VerifyViewModel : ViewModelExceptionHandle() {
    private val verifyRepository = VerifyRepository()

    private val verify: MutableLiveData<List<VerifyModel>> by lazy {
        MutableLiveData<List<VerifyModel>>()
    }

    fun getVerify(): LiveData<List<VerifyModel>> = verify

    init {
        viewModelScope.launch(exceptionHandler) {
            val verifyFetchResult = fetchVerify(getIdArray(10))
            verify.postValue(verifyFetchResult)
        }
    }

    private suspend fun fetchVerify(idArray: HashSet<Int>): List<VerifyModel>{
        return withContext(Dispatchers.IO){
            verifyRepository.getVerifyFromDb(idArray)
        }
    }

    private fun getIdArray(size : Int): HashSet<Int> {
        val s = HashSet<Int>(size)
        while (s.size < size) {
            s += (1..78).shuffled().random()
        }
        return s
    }
}