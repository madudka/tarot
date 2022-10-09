package com.madudka.tarot.utils

import android.app.ActivityManager

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

enum class SignType(val id: Int, val description: String) {
    Aries(1, "Овен"),
    Taurus(2, "Телец"),
    Gemini(3, "Близнецы"),
    Cancer(4, "Рак"),
    Leo(5, "Лев"),
    Virgo(6, "Дева"),
    Libra(7, "Весы"),
    Scorpio(8, "Скорпион"),
    Sagittarius(9,"Стрелец"),
    Capricorn(10,"Козерог"),
    Aquarius(11,"Водолей"),
    Pisces(12,"Рыбы")
}

enum class DayType(val id: Int, val description: String){
    today(1, "Сегодня"),
    yesterday(2, "Вчера"),
    tomorrow(3, "Завтра")
}












