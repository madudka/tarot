package com.madudka.tarot.viewmodel.divination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.madudka.tarot.model.DivinationCardModel
import com.madudka.tarot.model.repository.CardRepository
import com.madudka.tarot.viewmodel.ViewModelExceptionHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CardViewModel: ViewModelExceptionHandle() {
    private val repository = CardRepository()

    private val card: MutableLiveData<DivinationCardModel> by lazy {
        MutableLiveData<DivinationCardModel>()
    }

    fun getCard(): LiveData<DivinationCardModel> = card
//    fun getImage() = card.value?.image
//    fun getId() = card.value?.id

    fun loadDayCard(userNum: Int){
        viewModelScope.launch(exceptionHandler) {
            val dayCardFetchResult = fetchDayCard(userNum.customRandom())
            card.postValue(dayCardFetchResult)
        }
    }

    fun loadCardAdvice(){
        viewModelScope.launch(exceptionHandler) {
            val cardAdviceFetchResult = fetchCardAdvice()
            card.postValue(cardAdviceFetchResult)
        }
    }

    private suspend fun fetchDayCard(id: Int = (1..78).random()): DivinationCardModel{
        return withContext(Dispatchers.IO){
            repository.getDayCardFromDb(id)
        }
    }

    private suspend fun fetchCardAdvice(): DivinationCardModel{
        return withContext(Dispatchers.IO){
            repository.getCardAdviceFromDb((1..78).random())
        }
    }

    private fun Int.customRandom() = (this + (1..78).random()) % 78 + 1
}