package com.madudka.tarot.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
class ImagesEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "type")
    val type: Int,
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    val image: ByteArray
)