package com.madudka.tarot.viewmodel.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.madudka.tarot.model.CardModel
import com.madudka.tarot.model.repository.CardRepository
import com.madudka.tarot.viewmodel.ViewModelExceptionHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CardsViewModel : ViewModelExceptionHandle() {
    private val cardRepository = CardRepository()

    private val cards: MutableLiveData<List<CardModel>> by lazy {
        MutableLiveData<List<CardModel>>()
    }

    fun getCards(): LiveData<List<CardModel>> = cards

    init {
        viewModelScope.launch(exceptionHandler) {
            val cardsFetchResult = fetchCards()
            cards.postValue(cardsFetchResult)
        }
    }

    private suspend fun fetchCards(): List<CardModel>{
        return withContext(Dispatchers.IO){
            cardRepository.getAllCards()
        }
    }
}