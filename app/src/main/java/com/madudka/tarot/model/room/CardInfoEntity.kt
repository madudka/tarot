package com.madudka.tarot.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_info")
class CardInfoEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "common_straight")
    val common_straight: String,
    @ColumnInfo(name = "common_inverted")
    val inverted_straight: String,
    @ColumnInfo(name = "love_straight")
    val love_straight: String,
    @ColumnInfo(name = "love_inverted")
    val love_inverted: String,
    @ColumnInfo(name = "question_straight")
    val question_straight: String,
    @ColumnInfo(name = "question_inverted")
    val question_inverted: String,
    @ColumnInfo(name = "day")
    val day: String,
    @ColumnInfo(name = "advice")
    val advice: String
)
