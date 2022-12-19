package com.madudka.tarot.model

data class VerifyModel(
    val id: Int,
    val image: ByteArray,
    val inverted: Boolean = ((0..2).shuffled().random() == 0)
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