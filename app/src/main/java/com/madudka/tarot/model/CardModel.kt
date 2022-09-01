package com.madudka.tarot.model

data class CardModel(
    val id: Int,
    val name: String,
    val type: Int,
    val image: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CardModel

        if (id != other.id) return false
        if (name != other.name) return false
        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + image.contentHashCode()
        return result
    }
}