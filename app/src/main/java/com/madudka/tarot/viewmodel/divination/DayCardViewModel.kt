package com.madudka.tarot.viewmodel.divination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madudka.tarot.model.DivinationCardModel
import com.madudka.tarot.model.repository.DayCardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DayCardViewModel : ViewModel() {
    private val dayCardRepository = DayCardRepository()

    private val dayCard: MutableLiveData<DivinationCardModel> by lazy {
        MutableLiveData<DivinationCardModel>()
    }

    fun getDayCard() : LiveData<DivinationCardModel> = dayCard
    fun getImage() = dayCard.value?.image

    fun loadCard(userNum: Int){
        viewModelScope.launch {
            val dayCardFetchResult = fetchDayCard(userNum.customRandom())
            dayCard.postValue(dayCardFetchResult)
        }
    }

    //TODO: Расширить рандом после добавления полной базы
    private suspend fun fetchDayCard(id: Int = (1..22).random()): DivinationCardModel{
        return withContext(Dispatchers.IO){
            dayCardRepository.getDayCardFromDb(id)
        }
    }

    //TODO: Расширить рандом после добавления полной базы
    private fun Int.customRandom() = (this + (1..22).random()) % 22 + 1
}