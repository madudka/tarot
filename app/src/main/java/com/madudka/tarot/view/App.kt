package com.madudka.tarot.view

import android.graphics.drawable.Drawable
import com.madudka.tarot.model.room.TarotDatabase
import com.madudka.tarot.utils.Settings
import java.time.LocalDate
import kotlin.properties.Delegates

object App {
    lateinit var db: TarotDatabase
    lateinit var settings: Settings
    lateinit var cardBack: Drawable
    var online by Delegates.notNull<Boolean>()

    fun now() = LocalDate.now().toEpochDay()
}