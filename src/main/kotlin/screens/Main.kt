package screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import model.Calendar
import model.CustomColor
import model.YearTextInputError
import ui_components.CaledarColorsToolKit
import ui_components.YearTextField

@Composable
@Preview
fun App() {
    var yearText by remember { mutableStateOf("") }
    var inputError by remember { mutableStateOf(YearTextInputError.DifferentThan4Digits) }

    var calendar by remember { mutableStateOf(Calendar()) }
    var showCalendar by remember { mutableStateOf(false) }

    var calendarBgColor by remember { mutableStateOf(CustomColor(0f, 1f, 0f)) }
    var monthTxColor by remember { mutableStateOf(CustomColor(120f, 1f, 0.1f)) }
    var monthBgColor by remember { mutableStateOf(CustomColor(120f, 0.8f, 0.7f)) }
    var daysTxColor by remember { mutableStateOf(CustomColor(120f, 1f, 0.1f)) }
    var daysBgColor by remember { mutableStateOf(CustomColor(120f, 0.6f, 0.8f)) }
    var boxLinesColor by remember { mutableStateOf(CustomColor(120f, 1f, 0.4f)) }
    var separatorLinesColor by remember { mutableStateOf(CustomColor(120f, 1f, 0.4f)) }

    MaterialTheme {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxHeight().weight(0.6f).padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                YearTextField(
                    yearText = yearText,
                    onYearChange = {
                        yearText = it
                        inputError = if (yearText.length != 4) {
                            YearTextInputError.DifferentThan4Digits
                        } else if (!yearText.matches("\\d{4}".toRegex())) {
                            YearTextInputError.NotOnlyNumbers
                        } else if (yearText.toInt() !in 1600..9999) {
                            YearTextInputError.OutOfRange
                        } else YearTextInputError.AcceptedYear
                    },
                    inputError = inputError
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))

                Button(
                    modifier = Modifier.padding(8.dp),
                    enabled = !inputError.isWrong,
                    onClick = {
                        calendar = Calendar(yearText.toInt())
                        showCalendar = true
                    }
                ) {
                    Text("Make Calendar !")
                }

                Spacer(modifier = Modifier.height(8.dp))

                CaledarColorsToolKit(
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

            AnimatedVisibility(
                modifier = Modifier.weight(1.4f),
                visible = showCalendar,
                enter = fadeIn() + expandHorizontally()
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CalendarScreen(
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
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Days In Boxes!") {
        App()
    }
}
