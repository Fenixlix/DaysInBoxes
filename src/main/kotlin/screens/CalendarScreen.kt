package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Calendar
import model.CustomColor
import model.TwelveMonths
import model.toColor
import ui_components.CalendarMonth

@Composable
fun CalendarScreen(
    calendar: Calendar,
    calendarBgColor: CustomColor,
    monthBgColor: CustomColor,
    monthTxColor: CustomColor,
    daysBgColor: CustomColor,
    daysTxColor: CustomColor,
    boxLinesColor: CustomColor,
    separatorLinesColor: CustomColor
) {
    LazyVerticalGrid(
        modifier = Modifier.wrapContentSize().background(calendarBgColor.toColor()),
        columns = GridCells.Adaptive(240.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(TwelveMonths.values()) { month ->
            CalendarMonth(
                month.monthName,
                calendar.monthWeeks[month.ordinal],
                monthBackgroundColor = monthBgColor.toColor(),
                daysBackgroundColor = daysBgColor.toColor(),
                monthTextColor = monthTxColor.toColor(),
                daysTextColor = daysTxColor.toColor(),
                externalBorderColor = boxLinesColor.toColor(),
                separatorLinesColor = separatorLinesColor.toColor()
            )
        }
    }
}