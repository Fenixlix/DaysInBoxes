package ui_components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import model.YearTextInputError

@Composable
fun YearTextField(
    yearText: String,
    inputError: YearTextInputError,
    onYearChange: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = yearText,
            onValueChange = { onYearChange(it) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = { Text("Year") }
        )
        Text(
            text = inputError.message,
            color = if (inputError.isWrong) Color.Red else Color.Blue,
            textAlign = TextAlign.Center
        )
    }
}

