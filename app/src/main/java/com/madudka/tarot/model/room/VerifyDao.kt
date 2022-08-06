package com.madudka.tarot.model.room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface VerifyDao {
    @Query("SELECT image FROM images WHERE id IN (:idArray) AND type = :type")
    fun selectVerifyCards(idArray: Array<Int>, type: Int): List<ByteArray>
}