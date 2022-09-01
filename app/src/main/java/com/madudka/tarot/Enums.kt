package com.madudka.tarot

enum class DivinationType {
    DAY_CARD,
    ADVICE
}

enum class CardType(val id: Int) {
    MAJOR_ARCANA(1),
    WANDS(2),
    CUPS(3),
    SWORDS(4),
    PENTACLES(5)
}