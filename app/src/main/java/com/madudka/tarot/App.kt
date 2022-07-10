package com.madudka.tarot

import android.app.Application
import android.content.Intent
import com.madudka.tarot.model.room.TarotDatabase
import com.madudka.tarot.view.MainActivity

class App : Application() {
    companion object { lateinit var db: TarotDatabase}

    override fun onCreate() {
        super.onCreate()

        db = TarotDatabase.getInstance(applicationContext)

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}