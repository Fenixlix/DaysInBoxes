package ui_components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.DaysOfTheWeek
import model.TwelveMonths

@Composable
fun CalendarMonth(
    month: TwelveMonths,
    firstDayOfTheWeek: Int,
    isLeapYear: Boolean
) {
    val monthBoxWidth = 240.dp
    val weeks = getDaysOfWeeks(
        numberOfDaysOfMonth = month.numberOfDays,
        startingDayOfTheWeek = firstDayOfTheWeek,
        isLeapYear = isLeapYear
    )

    Column(
        modifier = Modifier.wrapContentSize().border(width = 4.dp, color = Color.DarkGray).padding(8.dp)
    ) {
        Text(
            modifier = Modifier.width(monthBoxWidth).border(width = 2.dp, color = Color.Green).padding(vertical = 4.dp),
            text = month.monthName,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.width(monthBoxWidth),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DaysOfTheWeek.values().iterator().forEach {
                Text(
                    modifier = Modifier.padding(4.dp).width(26.dp),
                    text = it.shortName,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        weeks.forEach { week ->
            Row(
                modifier = Modifier.width(monthBoxWidth),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                week.forEach {
                    Text(
                        modifier = Modifier.padding(4.dp).width(26.dp),
                        text = it?.toString() ?: " ",
                        textAlign = TextAlign.Right
                    )
                }
            }
        }

    }
}

fun getDaysOfWeeks(numberOfDaysOfMonth: Int, startingDayOfTheWeek: Int, isLeapYear: Boolean): List<List<Int?>> {
    val numberOfDays = if (numberOfDaysOfMonth == 28 && isLeapYear) 29 else numberOfDaysOfMonth
    var dayOfTheWeek = 0
    var drawBlank = true
    var day = 1

    var week = mutableListOf<Int?>()
    val weeks = mutableListOf<List<Int?>>()

    for (x in 1..35) {
        if (dayOfTheWeek == startingDayOfTheWeek) {
            drawBlank = false
        }
        if (drawBlank) {
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
    if (day < numberOfDaysOfMonth) {
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