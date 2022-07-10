package com.madudka.tarot.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
class CardsEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "type")
    val type: Int,
    @ColumnInfo(name = "other_name")
    val otherName: String?,
    @ColumnInfo(name = "straight")
    val straight: String,
    @ColumnInfo(name = "inverted")
    val inverted: String
)