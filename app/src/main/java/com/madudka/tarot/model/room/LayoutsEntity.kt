package com.madudka.tarot.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "layouts")
class LayoutsEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "type")
    val type: Int,
    @ColumnInfo(name = "card_count")
    val card_count: Int,
    @ColumnInfo(name = "signifier_count")
    val signifier_count: Int,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    val image: ByteArray?
)