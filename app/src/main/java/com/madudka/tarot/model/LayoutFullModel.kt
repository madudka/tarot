package com.madudka.tarot.model

data class LayoutFullModel(
    val id: Int,
    val name: String,
    val type: Int,
    val cardCount: Int,
    val signifierCount: Int,
    val description: String?,
    val image: ByteArray?
    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LayoutFullModel

        if (id != other.id) return false
        if (name != other.name) return false
        if (type != other.type) return false
        if (cardCount != other.cardCount) return false
        if (signifierCount != other.signifierCount) return false
        if (description != other.description) return false
        if (image != null) {
            if (other.image == null) return false
            if (!image.contentEquals(other.image)) return false
        } else if (other.image != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + type
        result = 31 * result + cardCount
        result = 31 * result + signifierCount
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (image?.contentHashCode() ?: 0)
        return result
    }
}