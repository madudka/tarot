package com.madudka.tarot.model.room

import androidx.room.Dao
import androidx.room.Query
import com.madudka.tarot.model.VerifyDaoModel

@Dao
interface VerifyDao {
    @Query("SELECT id, image FROM images WHERE id IN (:idArray) AND type = :type")
    fun selectVerifyCards(idArray: Array<Int>, type: Int): List<VerifyDaoModel>
}