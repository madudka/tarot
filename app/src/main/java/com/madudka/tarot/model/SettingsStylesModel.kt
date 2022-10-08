package com.madudka.tarot.model

import com.google.firebase.storage.StorageReference

data class SettingsStylesModel(
    val name: String,
    val pathRefCard: StorageReference,
    val pathRefCardBack: StorageReference
)