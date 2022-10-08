package com.madudka.tarot.model.repository

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class FirebaseRepository {
    private val storage = Firebase.storage.reference

    fun getAllPrefixes() = storage.listAll()
}