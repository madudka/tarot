package com.madudka.tarot.model.repository

import com.madudka.tarot.model.DivinationCardModel
import com.madudka.tarot.view.App.Companion.db

class CardAdviceRepository {
    private val dbAccess = db.getCardAdviceDao()

    fun getCardAdviceFromDb(id: Int): DivinationCardModel{
        return dbAccess.selectCardAdvice(id)
    }
}