package com.madudka.tarot.model.room

import androidx.room.Dao
import androidx.room.Query
import com.madudka.tarot.model.LayoutFullModel
import com.madudka.tarot.model.LayoutModel

@Dao
interface LayoutsDao {
    @Query("SELECT id, name, type FROM layouts")
    fun selectAllLayouts(): List<LayoutModel>

    @Query("SELECT id, name, type, card_count as cardCount, signifier_count as signifierCount" +
            ", description, image" +
            " FROM layouts WHERE id=:id")
    fun selectLayout(id: Int): LayoutFullModel
}