package com.madudka.tarot.model.repository

import com.madudka.tarot.view.App.Companion.db
import com.madudka.tarot.model.DayCardModel

class DayCardRepository{
    private val dbAccess = db.getDayCardDao()

    suspend fun getDayCardFromDb(id: Int): DayCardModel{
           return dbAccess.selectDayCard(id)
    }
}