package com.madudka.tarot.viewmodel.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madudka.tarot.model.repository.FirebaseRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SettingsViewModel : ViewModel() {
    private val firebaseRepository = FirebaseRepository()

    private val prefixes: MutableLiveData<List<String>> by lazy { MutableLiveData<List<String>>() }

    fun getPrefixes(): LiveData<List<String>> = prefixes

    init {
        viewModelScope.launch {
            val fetchPrefixesResult = fetchPrefixes().prefixes.map { it.name }
            prefixes.postValue(fetchPrefixesResult)
        }
    }

    private suspend fun fetchPrefixes() = firebaseRepository.getAllPrefixes().await()
}