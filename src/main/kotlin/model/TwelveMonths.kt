package model

enum class TwelveMonths(val monthName: String, val numberOfDays : Int) {
    ONE("Enero", 31),
    TWO("Febrero", 28),
    THREE("Marzo", 31),
    FOUR("Abril", 30),
    FIVE("Mayo", 31),
    SIX("Junio", 30),
    SEVEN("Julio", 31),
    EIGHT("Agosto", 31),
    NINE("Septiembre", 30),
    TEN("Octubre", 31),
    ELEVEN("Noviembre", 30),
    TWELVE("Diciembre", 31)
}