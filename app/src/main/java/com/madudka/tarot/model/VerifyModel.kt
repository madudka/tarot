package com.madudka.tarot.model

import kotlin.random.Random

data class VerifyModel(
    val id: Int,
    val image: ByteArray,
    val inverted: Boolean = Random.nextBoolean()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as VerifyModel

        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = 4596
        result = 31 * result + image.contentHashCode()
        return result
    }
}

fun VerifyDaoModel.toVerifyModel() = VerifyModel(
    this.id,
    this.image
)