package com.madudka.tarot.viewmodel.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madudka.tarot.model.CardModel
import com.madudka.tarot.model.repository.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CardsViewModel : ViewModel() {
    private val cardRepository = CardRepository()

    private val cards: MutableLiveData<List<CardModel>> by lazy {
        MutableLiveData<List<CardModel>>()
    }

    fun getCards(): LiveData<List<CardModel>> = cards

    init {
        viewModelScope.launch {
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