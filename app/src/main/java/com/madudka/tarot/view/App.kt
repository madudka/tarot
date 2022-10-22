package com.madudka.tarot.view

import android.app.Application
import android.content.Intent
import android.graphics.drawable.Drawable
import com.madudka.tarot.glide.loadCardBackImage
import com.madudka.tarot.model.room.TarotDatabase
import com.madudka.tarot.utils.Settings
import java.time.LocalDate
import kotlin.properties.Delegates

class App : Application() {
    companion object {
        lateinit var db: TarotDatabase
        lateinit var settings: Settings
        var now by Delegates.notNull<Long>()
        lateinit var cardBack: Drawable
        var online by Delegates.notNull<Boolean>()
    }

    override fun onCreate() {
        super.onCreate()

        db = TarotDatabase.getInstance(applicationContext)
        settings = Settings.getInstance(applicationContext)
        now = LocalDate.now().toEpochDay()
        online = false

        //TODO Загрузка изображения
        loadCardBackImage(applicationContext)

        //TODO: Удалить очистку настроек к релизу
        settings.clear()

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}