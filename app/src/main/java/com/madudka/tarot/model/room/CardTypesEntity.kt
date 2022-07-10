package com.madudka.tarot.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_types")
class CardTypesEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "type")
    val type: String
)