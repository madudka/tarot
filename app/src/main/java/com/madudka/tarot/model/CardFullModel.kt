package com.madudka.tarot.model

import com.google.firebase.storage.StorageReference
import com.madudka.tarot.utils.getPathRefCard
import com.madudka.tarot.view.App

data class CardFullModel(
    val id: Int,
    val name: String,
    val type: String,
    val other_name: String,
    val straight: String,
    val inverted: String,
    val image: ByteArray,
    val commonStraight: String,
    val commonInverted: String,
    val loveStraight: String,
    val loveInverted: String,
    val questionStraight: String,
    val questionInverted: String,
    val day: String,
    val advice: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CardFullModel

        if (id != other.id) return false
        if (name != other.name) return false
        if (type != other.type) return false
        if (other_name != other.other_name) return false
        if (straight != other.straight) return false
        if (inverted != other.inverted) return false
        if (!image.contentEquals(other.image)) return false
        if (commonStraight != other.commonStraight) return false
        if (commonInverted != other.commonInverted) return false
        if (loveStraight != other.loveStraight) return false
        if (loveInverted != other.loveInverted) return false
        if (questionStraight != other.questionStraight) return false
        if (questionInverted != other.questionInverted) return false
        if (day != other.day) return false
        if (advice != other.advice) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + other_name.hashCode()
        result = 31 * result + straight.hashCode()
        result = 31 * result + inverted.hashCode()
        result = 31 * result + image.contentHashCode()
        result = 31 * result + commonStraight.hashCode()
        result = 31 * result + commonInverted.hashCode()
        result = 31 * result + loveStraight.hashCode()
        result = 31 * result + loveInverted.hashCode()
        result = 31 * result + questionStraight.hashCode()
        result = 31 * result + questionInverted.hashCode()
        result = 31 * result + day.hashCode()
        result = 31 * result + advice.hashCode()
        return result
    }
}
