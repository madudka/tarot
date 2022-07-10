package com.madudka.tarot.viewmodel.divination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madudka.tarot.model.DayCardModel
import com.madudka.tarot.model.repository.DayCardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DayCardViewModel : ViewModel() {
    private val dayCardRepository = DayCardRepository()

    private val dayCard: MutableLiveData<DayCardModel> by lazy {
        MutableLiveData<DayCardModel>()
    }

    fun loadCard(userNum: Int){
        viewModelScope.launch {
            val dayCardModel = fetchDayCard(userNum.customRandom())
            dayCard.postValue(dayCardModel)
        }
    }

//    init {
//        viewModelScope.launch {
//            dayCard.postValue(fetchDayCard(_userNum.value.customRandom()))
//        }
//    }

    private suspend fun fetchDayCard(id: Int = (1..22).random()): DayCardModel{
        return withContext(Dispatchers.IO){
            dayCardRepository.getDayCardFromDb(id)
        }
    }

    fun getDayCard() : LiveData<DayCardModel> = dayCard
    fun getImage() = dayCard.value?.image

    private fun Int.customRandom() = (this + (1..22).random()) % 22 + 1
}