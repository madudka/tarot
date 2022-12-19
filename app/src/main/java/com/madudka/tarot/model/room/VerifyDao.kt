package com.madudka.tarot.model.room

import androidx.room.Dao
import androidx.room.Query
import com.madudka.tarot.model.VerifyDaoModel

@Dao
interface VerifyDao {
    @Query("SELECT id, image FROM images WHERE id IN (:idArray)")
    fun selectVerifyCards(idArray: HashSet<Int>): List<VerifyDaoModel>
}