package com.madudka.tarot.model.room

import androidx.room.Dao
import androidx.room.Query
import com.madudka.tarot.model.CardModel

@Dao
interface CardsDao {
    @Query("SELECT c.id, c.name, c.type, img.image" +
            " FROM cards c, images img" +
            " WHERE img.id = c.id")
    fun selectAllCards(): List<CardModel>
}