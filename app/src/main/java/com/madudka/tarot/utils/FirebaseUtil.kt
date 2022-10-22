package com.madudka.tarot.utils

import com.google.firebase.storage.FirebaseStorage

private val storageRef = FirebaseStorage.getInstance().reference

fun getPathRefCard(style: String, id: Int) = storageRef.child("$style/$id.jpg")