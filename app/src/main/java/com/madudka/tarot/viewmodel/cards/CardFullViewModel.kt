package com.madudka.tarot.viewmodel.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madudka.tarot.model.CardFullModel
import com.madudka.tarot.model.repository.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CardFullViewModel : ViewModel() {
    private val cardRepository = CardRepository()

    private val cardFull: MutableLiveData<CardFullModel> by lazy {
        MutableLiveData<CardFullModel>()
    }

    fun getCardFull(): LiveData<CardFullModel> = cardFull
    fun getImage() = cardFull.value?.image

    fun loadCardFull(id: Int){
        viewModelScope.launch {
            val cardFullFetchResult = fetchCardFull(id)
            cardFull.postValue(cardFullFetchResult)
        }
    }

    private suspend fun fetchCardFull(id: Int): CardFullModel{
        return withContext(Dispatchers.IO){
            cardRepository.getCardFull(id)
        }
    }
}