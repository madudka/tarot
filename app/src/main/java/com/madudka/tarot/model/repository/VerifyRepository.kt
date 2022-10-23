package com.madudka.tarot.model.repository

import com.madudka.tarot.model.VerifyModel
import com.madudka.tarot.model.toVerifyModel
import com.madudka.tarot.view.App.db

class VerifyRepository {
    private val dbAccess = db.getVerifyDao()

    fun getVerifyFromDb(idArray: Array<Int>, type: Int): List<VerifyModel>{
        return dbAccess.selectVerifyCards(idArray, type).map { it.toVerifyModel() }
    }
}