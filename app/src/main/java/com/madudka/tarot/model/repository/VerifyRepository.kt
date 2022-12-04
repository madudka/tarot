package com.madudka.tarot.model.repository

import com.madudka.tarot.model.VerifyModel
import com.madudka.tarot.model.toVerifyModel
import com.madudka.tarot.view.App.db

class VerifyRepository {
    fun getVerifyFromDb(idArray: Array<Int>, type: Int): List<VerifyModel>{
        return db.getVerifyDao().selectVerifyCards(idArray, type).map { it.toVerifyModel() }
    }
}