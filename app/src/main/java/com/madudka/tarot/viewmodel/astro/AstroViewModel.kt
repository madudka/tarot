package com.madudka.tarot.viewmodel.astro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madudka.tarot.DayType
import com.madudka.tarot.SignType
import com.madudka.tarot.model.AstroModel
import com.madudka.tarot.model.api.API
import com.madudka.tarot.model.repository.AstroRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class AstroViewModel : ViewModel() {

    private val astroRepository = AstroRepository(API())

    private val astro: MutableLiveData<AstroModel> by lazy { MutableLiveData<AstroModel>() }
    private val error: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun getAstro(): LiveData<AstroModel> = astro
    fun getError(): LiveData<String> = error

    fun loadAstro(signType: SignType, dayType: DayType){
        viewModelScope.launch(exceptionHandler) {
            val fetchResult = fetchAstro(signType.name, dayType.name)
            if (fetchResult.isSuccessful) astro.postValue(fetchResult.body())
            else error.postValue("Error: ${fetchResult.message()}")
        }
    }

    private suspend fun fetchAstro(sign: String, day: String): Response<AstroModel>{
        return withContext(Dispatchers.IO + exceptionHandler){
            astroRepository.getAstroData(sign, day)
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        error.postValue("Exception handled: ${throwable.localizedMessage}")
    }
}