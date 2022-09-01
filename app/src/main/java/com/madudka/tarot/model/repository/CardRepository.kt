package com.madudka.tarot.model.repository

import com.madudka.tarot.model.CardModel
import com.madudka.tarot.model.DivinationCardModel
import com.madudka.tarot.view.App.Companion.db

class CardRepository {
    fun getCardAdviceFromDb(id: Int) : DivinationCardModel = db.getCardAdviceDao().selectCardAdvice(id)

    fun getDayCardFromDb(id: Int) : DivinationCardModel = db.getDayCardDao().selectDayCard(id)

    fun getAllCards() : List<CardModel> = db.getCards().selectAllCards()
}