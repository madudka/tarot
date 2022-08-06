package com.madudka.tarot.model.room

import androidx.room.Dao
import androidx.room.Query
import com.madudka.tarot.model.DivinationCardModel

@Dao
interface CardAdviceDao {
    @Query("SELECT c.id, c.name, ct.type, IFNULL(c.other_name, '') AS otherName, ci.advice AS info, img.image" +
            " FROM cards c, card_types ct, card_info ci, images img" +
            " WHERE c.id = :id AND ct.id = c.type AND ci.id = :id AND img.id = :id")
    fun selectCardAdvice(id: Int): DivinationCardModel
}