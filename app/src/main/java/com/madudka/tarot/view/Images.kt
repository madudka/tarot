package com.madudka.tarot.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory

fun ByteArray.toBitmap(): Bitmap = BitmapFactory.decodeByteArray(this, 0, size)