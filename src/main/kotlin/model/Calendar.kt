package model

class Calendar(private val year: Int = 0) {

    private var _doomsday = 0
    private var _monthsFirstDay = emptyList<Int>()
    private var _monthsWeeks = emptyList<List<List<Int?>>>()
    private var _isLeapYear = false
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

    init {
        if (year in 1600..9999) {
            _doomsday = getDoomsDayOfYear()

            _monthsFirstDay = getMonthsFirstDay()

            _monthsWeeks = calculateWeeks()
        }
    }

    // Exposed Data
    fun isLeapYear() = _isLeapYear
    val monthWeeks = _monthsWeeks

    private fun getDoomsDayOfYear(): Int {
        val secondHalf = year.mod(100)
        val firstHalf = (year - secondHalf) / 100

        _isLeapYear = ((secondHalf != 0 && secondHalf.mod(4) == 0)
                || (secondHalf == 0 && (firstHalf * 100).mod(400) == 0))

        val anchorDay = ((firstHalf.mod(4)) * 5 + 2).mod(7)
        val shiftDay = (secondHalf + (secondHalf / 4)).mod(7)
        val doomsDay = anchorDay + shiftDay

        return if (doomsDay >= 7) doomsDay.mod(7) else doomsDay
    }

    private fun getMonthsFirstDay(): List<Int> {
        val montFirstDay = mutableListOf<Int>()
        for (i in 0..11) {
            montFirstDay.add(getDayOne(_monthsDoomsDays[i]))
        }
        return montFirstDay
    }

    private fun getDayOne(monthDoomsDay: Int) = (_doomsday - (monthDoomsDay.mod(7) - 1) + 7).mod(7)

    private fun getDaysOfWeeks(month: TwelveMonths): List<List<Int?>> {
        val numberOfDays = if (month.numberOfDays == 28 && _isLeapYear) 29 else month.numberOfDays
        var dayOfTheWeek = 0
        var drawBlank: Boolean? = null
        var day = 1

        var week = mutableListOf<Int?>()
        val weeks = mutableListOf<List<Int?>>()

        for (x in 1..35) {
            if (dayOfTheWeek == _monthsFirstDay[month.ordinal] && drawBlank == null) {
                drawBlank = false
            }
            if (drawBlank != false) {
                week.add(null)
            } else {
                week.add(day++)
                if (day > numberOfDays) drawBlank = true
            }
            dayOfTheWeek++
            // Reset the week
            if (x.mod(7) == 0) {
                weeks.add(week)
                week = arrayListOf()
                dayOfTheWeek = 0
            }
        }
        day--
        if (day < numberOfDays) {
            var diff = numberOfDays - day++
            val newList = weeks[0].map {
                if (diff > 0) {
                    diff--
                    day++
                } else
                    it
            }
            weeks.removeAt(0)
            weeks.add(0, newList)
        }
        return weeks
    }

    private fun calculateWeeks(): List<List<List<Int?>>> {
        val monthWeeks = mutableListOf<List<List<Int?>>>()
        TwelveMonths.values().iterator().forEach {
            monthWeeks.add(getDaysOfWeeks(it))
        }
        return monthWeeks
    }

    private fun Int.toDayOfWeek(): String {
        return if (this in 0..6) {
            DaysOfTheWeek.values()[this].longName
        } else {
            "Int must be between 0 and 6"
        }
    }

    fun printCalendar(month: TwelveMonths) {
        val numberOfDays = month.numberOfDays
        val startingDayOfTheWeek = _monthsFirstDay[month.ordinal] //Starting day of the week
        var dayOfTheWeek = 0
        var drawBlank: Boolean? = null
        var day = 1

        println("Year: $year ${month.monthName}  DD: ${_doomsday.toDayOfWeek()}")
        println("_______________________")
        println("  D  L  M  M  J  V  S")
        for (x in 1..35) {
            if (dayOfTheWeek == startingDayOfTheWeek && drawBlank == null) {
                drawBlank = false
            }
            if (drawBlank != false) {
                print("   ")
            } else {
                if (day < 10) {
                    print(" ")
                }
                print(" ${day++}")
                if (day > numberOfDays) {
                    drawBlank = true
                }
            }
            dayOfTheWeek++
            // Reset the week
            if (x.mod(7) == 0) {
                println()
                dayOfTheWeek = 0
            }
        }
        day--
        if (day < numberOfDays) {
            val diff = numberOfDays - day++
            repeat(diff) {
                print(" ${day++}")
            }
            println()
        }
        println("_______________________")
    }
}


fun main() {
    val calendar = Calendar(2025)
    calendar.printCalendar(TwelveMonths.THREE)
    println("Is leap year : ${calendar.isLeapYear()}\n")

    print(calendar.monthWeeks[TwelveMonths.THREE.ordinal])
}