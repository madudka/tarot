package com.madudka.tarot.model.room

import androidx.room.Dao
import androidx.room.Query
import com.madudka.tarot.model.CardFullModel

@Dao
interface CardFullDao {

    @Query("SELECT c.id, c.name, ct.type, IFNULL(c.other_name, '') AS other_name, c.straight, c.inverted, img.image," +
            " ci.common_straight as commonStraight, ci.common_inverted as commonInverted," +
            " ci.love_straight as loveStraight, ci.love_inverted as loveInverted," +
            " ci.question_straight as questionStraight, ci.question_inverted as questionInverted," +
            " ci.day, ci.advice" +
            " FROM cards c" +
            " LEFT JOIN card_types ct ON ct.id = c.type" +
            " LEFT JOIN images img ON img.id = c.id" +
            " LEFT JOIN card_info ci ON ci.id = c.id" +
            " WHERE c.id = :id")
    fun selectCard(id: Int) : CardFullModel
}