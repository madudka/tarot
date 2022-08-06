package com.madudka.tarot.model.repository

import com.madudka.tarot.view.App.Companion.db
import com.madudka.tarot.model.DivinationCardModel

class DayCardRepository {
    private val dbAccess = db.getDayCardDao()

    fun getDayCardFromDb(id: Int): DivinationCardModel{
           return dbAccess.selectDayCard(id)
    }
}