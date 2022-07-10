package com.madudka.tarot.model

data class DayCardModel(
    val id: Int,
    val name: String,
    val type: String,
    val otherName: String,
    val info: String,
    val image: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DayCardModel

        if (id != other.id) return false
        if (info != other.info) return false
        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + info.hashCode()
        result = 31 * result + image.contentHashCode()
        return result
    }
}

//fun DayCardEntity.toModel() = DayCardModel(
//    id = this.id,
//    info = this.day
//)