package screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import model.Calendar
import model.CalendarDistribution
import model.CustomColor
import ui_components.UserControls
import ui_components.YearInputControls

@Composable
@Preview
fun App() {

    // Windows visibility variables
    var showCalendar: Boolean? by remember { mutableStateOf(null) }
    var showCalendarColorToolkit by remember { mutableStateOf(false) }

    // Calendar properties variables
    var calendar by remember { mutableStateOf(Calendar()) }
    var calendarWindowSize by remember { mutableStateOf(CalendarDistribution.FourColumn) }
    var calendarBgColor by remember { mutableStateOf(CustomColor(0f, 1f, 0f)) }
    var monthTxColor by remember { mutableStateOf(CustomColor(120f, 1f, 0.1f)) }
    var monthBgColor by remember { mutableStateOf(CustomColor(120f, 0.8f, 0.7f)) }
    var daysTxColor by remember { mutableStateOf(CustomColor(120f, 1f, 0.1f)) }
    var daysBgColor by remember { mutableStateOf(CustomColor(120f, 0.6f, 0.8f)) }
    var boxLinesColor by remember { mutableStateOf(CustomColor(120f, 1f, 0.4f)) }
    var separatorLinesColor by remember { mutableStateOf(CustomColor(120f, 1f, 0.4f)) }

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            YearInputControls(
                showCalendar = showCalendar,
                currentYear = calendar.year,
                onButtonClick = {
                    if (it != null) {
                        calendar = Calendar(it)
                    }
                    showCalendar = showCalendar?.not() ?: true
                })

            Spacer(modifier = Modifier.height(16.dp))

            UserControls(calendarWindowSize = calendarWindowSize,
                showColorToolkit = showCalendarColorToolkit,
                changeCalendarSize = { calendarWindowSize = it },
                toggleShowColorToolkit = { showCalendarColorToolkit = !showCalendarColorToolkit }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // ----- CALENDAR COLORS TOOLKIT WINDOW -----
        if (showCalendarColorToolkit) {
            val winPos by remember { mutableStateOf(WindowPosition(alignment = Alignment.Center)) }
            Window(
                onCloseRequest = { showCalendarColorToolkit = !showCalendarColorToolkit },
                title = "Calendar Color Toolkit",
                resizable = false,
                focusable = false,
                state = WindowState(size = DpSize(400.dp, 600.dp))
            ) {
                CaledarColorsToolKitScreen(
                    calendarBackground = calendarBgColor,
                    monthBackgroundColor = monthBgColor,
                    monthTextColor = monthTxColor,
                    daysBackgroundColor = daysBgColor,
                    daysTextColor = daysTxColor,
                    boxLinesColor = boxLinesColor,
                    separatorLinesColor = separatorLinesColor,
                    onMonthBackgroundColorChange = { monthBgColor = it },
                    onMonthTextColorChange = { monthTxColor = it },
                    onDaysBackgroundColorChange = { daysBgColor = it },
                    onDaysTextColorChange = { daysTxColor = it },
                    onBoxLinesColorChange = { boxLinesColor = it },
                    onSeparatorLinesColorChange = { separatorLinesColor = it },
                    onCalendarColorChange = { calendarBgColor = it }
                )
            }
        }

        // ----- CALENDAR WINDOW -----
        if (showCalendar == true) {
            Window(
                title = "Calendar of ${calendar.year}",
                onCloseRequest = { showCalendar = !showCalendar!! },
                focusable = false,
                state = WindowState(size = calendarWindowSize.size)
            ) {
                CalendarScreen(
                    isVertical = calendarWindowSize.isVertical,
                    calendar = calendar,
                    calendarBgColor = calendarBgColor,
                    monthBgColor = monthBgColor,
                    monthTxColor = monthTxColor,
                    daysBgColor = daysBgColor,
                    daysTxColor = daysTxColor,
                    boxLinesColor = boxLinesColor,
                    separatorLinesColor = separatorLinesColor
                )
            }
        }
    }
}


fun main() = application {
    Window(
        title = "Days In Boxes!",
        onCloseRequest = ::exitApplication,
        state = WindowState(size = DpSize(400.dp, 440.dp),),
        resizable = false
    ) {
        App()
    }
}
