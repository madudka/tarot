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

//<string-array name="layouts_filer_list">
enum class LayoutType(val id: Int, val description: String) {
    START(1,"Начальные"),
    FUTURE(2, "На будущее"),
    DOMESTIC(3, "Бытовые ситуации"),
    LOVE(4, "Любовь"),
    WORK(5, "Работа/бизнес/карьера"),
    SELF_DISCOVERY(6, "Самопознание"),
    HEALTH(7, "Здоровье"),
    CHILDREN(8, "Дети"),
    TRAVEL(9, "Поездки/переезды/путешествия")
}