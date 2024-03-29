package com.madudka.tarot.model.repository

import com.madudka.tarot.model.CardFullModel
import com.madudka.tarot.model.CardModel
import com.madudka.tarot.model.DivinationCardModel
import com.madudka.tarot.view.App.db

class CardRepository {
    fun getCardAdviceFromDb(id: Int) : DivinationCardModel = db.getCardAdviceDao().selectCardAdvice(id)

    fun getDayCardFromDb(id: Int) : DivinationCardModel = db.getDayCardDao().selectDayCard(id)

    fun getAllCards() : List<CardModel> = db.getCardsDao().selectAllCards()

    fun getCardFull(id: Int) : CardFullModel = db.getCardFullDao().selectCard(id)
}