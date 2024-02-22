package model

class Calendar(year: Int) {
    private var _year = year
    private var _isLeapYear = false
    private val _doomsday = getDoomsDayOfYear()

    private val _monthsDoomsDays = arrayOf(
        if (_isLeapYear) 4 else 3,      //3/1 or 4/1
        if (_isLeapYear) 29 else 28,    // last day
        14,                             // 14/3 "pi day"
        4,                              // 4/4
        9,                              // 9/5
        6,                              // 6/6
        11,                             // 11/7
        8,                              // 8/8
        5,                              // 5/9
        10,                             // 10/10
        7,                              // 7/11
        12                              // 12/12
    )

    val monthsPlusFirstDay = getMonthsFirstDay()

    private fun getDoomsDayOfYear(): Int {
        val secondHalf = _year.mod(100)
        val firstHalf = (_year - secondHalf) / 100

        _isLeapYear = ((secondHalf != 0 && secondHalf.mod(4) == 0)
                || (secondHalf == 0 && (firstHalf * 100).mod(400) == 0))

        val anchorDay = ((firstHalf.mod(4)) * 5 + 2).mod(7)
        val shiftDay = (secondHalf + (secondHalf / 4)).mod(7)
        val doomsDay = anchorDay + shiftDay

        return if (doomsDay >= 7) doomsDay.mod(7) else doomsDay
    }

    private fun getMonthsFirstDay(): Map<Int, Int> {
        val montFirstDayMap = mutableMapOf<Int, Int>()
        for (i in 0..11) {
            montFirstDayMap[i] = getDayOne(_monthsDoomsDays[i])
        }
        return montFirstDayMap
    }

    private fun getDayOne(monthDoomsDay: Int) = (_doomsday - (monthDoomsDay.mod(7) - 1) + 7).mod(7)

    private fun Int.toDayOfWeek(): String {
        return if (this in 0..6) {
            DaysOfTheWeek.values()[this].longName
        } else {
            "Int must be between 0 and 6"
        }
    }

    fun isLeapYear() = _isLeapYear

    fun printCalendar(month: TwelveMonths) {
        val numberOfDays = month.numberOfDays
        val startingDayOfTheWeek = monthsPlusFirstDay[month.ordinal] //Starting day of the week
        var dayOfTheWeek = 0
        var drawBlank = true
        var day = 1

        println("Year: $_year ${month.monthName}  DD: ${_doomsday.toDayOfWeek()}")
        println("_______________________")
        println("  D  L  M  M  J  V  S")
        for (x in 1..35) {
            if (dayOfTheWeek == startingDayOfTheWeek) {
                drawBlank = false
            }
            if (drawBlank) {
                print("   ")
            } else {
                if (day < 10) {
                    print(" ")
                }
                print(" ${day++}")
                if (day > numberOfDays) drawBlank = true
            }
            dayOfTheWeek++
            // Reset the week
            if (x.mod(7) == 0) {
                println()
                dayOfTheWeek = 0
            }
        }
        println("_______________________")
    }
}

fun main() {
    var calendar = Calendar(2024)
    calendar.printCalendar(TwelveMonths.TWO)
    println("Is leap year : ${calendar.isLeapYear()}\n")

    calendar = Calendar(1800)
    calendar.printCalendar(TwelveMonths.ONE)
    println("Is leap year? : ${calendar.isLeapYear()}\n")

}