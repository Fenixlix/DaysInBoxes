package ui_components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.YearTextInputError

@Composable
fun YearInputControls(
    showCalendar: Boolean?,
    currentYear: Int,
    onButtonClick: (Int?) -> Unit
) {
    var yearText by remember { mutableStateOf("") }
    var inputError by remember { mutableStateOf(YearTextInputError.DifferentThan4Digits) }

    Column(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
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
                onButtonClick(
                    if (yearText.toInt() != currentYear) {
                        yearText.toInt()
                    } else null
                )
            }
        ) {
            val buttonMessage = when {
                yearText.isBlank() -> "Make Calendar !"
                (yearText.isNotBlank() && yearText.toInt() != currentYear) -> "Make Calendar !"
                showCalendar == true -> "Hide Calendar"
                showCalendar == false -> "Show Calendar"
                else -> "Make Calendar !"
            }
            Text(text = buttonMessage)
        }
    }
}