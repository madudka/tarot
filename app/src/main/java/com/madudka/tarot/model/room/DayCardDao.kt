package com.madudka.tarot.model.room

import androidx.room.Dao
import androidx.room.Query
import com.madudka.tarot.model.DayCardModel

@Dao
interface DayCardDao {
    @Query("SELECT c.id, c.name, ct.type, IFNULL(c.other_name, '') as otherName, ci.day as info, img.image" +
            " FROM cards c, card_types ct, card_info ci, images_classic img" +
            " WHERE c.id =:id AND ct.id = c.type AND ci.id = :id AND img.id = :id")
    suspend fun selectDayCard(id: Int) : DayCardModel
}