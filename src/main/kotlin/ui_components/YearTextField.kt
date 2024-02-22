package ui_components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import model.YearTextInputError

@Composable
fun YearTextField(
    yearText: String,
    inputError: YearTextInputError,
    onYearChange: (String) -> Unit
) {

    Column(
        modifier = Modifier.border(
            width = 4.dp,
            color = Color.Black,
            shape = RoundedCornerShape(4.dp)
        ).padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
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

