package com.madudka.tarot.viewmodel.divination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madudka.tarot.model.DivinationCardModel
import com.madudka.tarot.model.repository.CardAdviceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CardAdviceViewModel : ViewModel() {
    private val cardAdviceRepository = CardAdviceRepository()

    private val cardAdvice: MutableLiveData<DivinationCardModel> by lazy{
        MutableLiveData<DivinationCardModel>()
    }

    fun getCardAdvice(): LiveData<DivinationCardModel> = cardAdvice
    fun getImage() = cardAdvice.value?.image

    fun loadCard() {
        viewModelScope.launch {
            val cardAdviceFetchResult = fetchCardAdvice()
            cardAdvice.postValue(cardAdviceFetchResult)
        }
    }

    //TODO: Расширить рандом после добавления полной базы
    private suspend fun fetchCardAdvice(): DivinationCardModel{
        return withContext(Dispatchers.IO){
            cardAdviceRepository.getCardAdviceFromDb((1..22).random())
        }
    }
}