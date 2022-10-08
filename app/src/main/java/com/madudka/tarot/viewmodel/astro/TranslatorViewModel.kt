package com.madudka.tarot.viewmodel.astro

import android.util.LruCache
import androidx.lifecycle.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import com.madudka.tarot.model.AstroModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await

private const val NUM_TRANSLATORS = 1
class TranslatorViewModel : ViewModel() {

    private val description: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    private val mood: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    private val color: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    private val descriptionFlag: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    private val moodFlag: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    private val colorFlag: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    fun getDescription(): LiveData<String> = description
    fun getMood(): LiveData<String> = mood
    fun getColor(): LiveData<String> = color
    fun getTranslating(): Flow<Boolean> = combine(
            descriptionFlag.asFlow(),
            moodFlag.asFlow(),
            colorFlag.asFlow()
        ){ descriptionFlag, moodFlag, colorFlag ->
            descriptionFlag && moodFlag && colorFlag
        }.onStart { delay(5000) }



    private var modelLoadTask: Task<Void> = Tasks.forCanceled()

    private val translators = object : LruCache<TranslatorOptions, Translator>(NUM_TRANSLATORS) {
        override fun create(options: TranslatorOptions): Translator {
            return Translation.getClient(options)
        }

        override fun entryRemoved(
            evicted: Boolean,
            key: TranslatorOptions?,
            oldValue: Translator?,
            newValue: Translator?
        ) {
            oldValue?.close()
        }
    }

    private fun getTranslationListener(receiver: MutableLiveData<String>) = OnCompleteListener<String> {
        if (it.isSuccessful) receiver.postValue(it.result)
        else if (it.isCanceled) return@OnCompleteListener
        else receiver.postValue(it.exception?.message)
    }

    fun translateAstro(astro: AstroModel) {
        viewModelScope.launch {

            fetchTranslation(astro.description, descriptionFlag).addOnCompleteListener(
                getTranslationListener(description)
            )
            fetchTranslation(astro.mood, moodFlag).addOnCompleteListener(
                getTranslationListener(mood)
            )
            fetchTranslation(astro.color, colorFlag).addOnCompleteListener(
                getTranslationListener(color)
            )
        }
    }

    private suspend fun fetchTranslation(text: String, flag: MutableLiveData<Boolean>): Task<String> {
        return withContext(Dispatchers.Default) {
            val options = TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage(TranslateLanguage.RUSSIAN)
                .build()
            val translator = translators[options]

            modelLoadTask = translator.downloadModelIfNeeded()

            modelLoadTask.onSuccessTask { translator.translate(text) }
                .addOnCompleteListener {
                    flag.postValue(true)
                }
        }
    }
}
