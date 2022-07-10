package com.madudka.tarot.model.repository

import com.madudka.tarot.App.Companion.db
import com.madudka.tarot.model.DayCardModel
//import com.madudka.tarot.model.toModel

class DayCardRepository{
    private val dbAccess = db.getDayCardDao()

    suspend fun getDayCardFromDb(id: Int): DayCardModel{
           return dbAccess.selectDayCard(id)//.toModel()
    }
}