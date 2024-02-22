import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import model.TwelveMonths
import model.YearTextInputError
import ui_components.CalendarMonth
import ui_components.YearTextField

@Composable
@Preview
fun App() {
    var yearText by remember { mutableStateOf("") }
    var inputError by remember { mutableStateOf(YearTextInputError.DifferentThan4Digits) }

    var calendar by remember { mutableStateOf(Calendar(2024)) }
    var showCalendar by remember { mutableStateOf(false) }

    MaterialTheme {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxHeight().weight(0.8f),
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
                Spacer(modifier = Modifier.padding(vertical = 16.dp))

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
            }

            if (showCalendar) {
                LazyVerticalGrid(
                    modifier = Modifier.weight(1.2f),
                    columns = GridCells.Adaptive(240.dp),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(TwelveMonths.values()) { month ->
                        CalendarMonth(month, calendar.monthsPlusFirstDay[month.ordinal]!!, calendar.isLeapYear())
                    }
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
