package ui_components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.CustomColor

@Composable
fun CaledarColorsToolKit(
    calendarBackground: CustomColor,
    monthBackgroundColor: CustomColor,
    daysBackgroundColor: CustomColor,
    monthTextColor: CustomColor,
    daysTextColor: CustomColor,
    boxLinesColor: CustomColor,
    separatorLinesColor: CustomColor,
    onCalendarColorChange: (CustomColor) -> Unit,
    onMonthBackgroundColorChange: (CustomColor) -> Unit,
    onDaysBackgroundColorChange: (CustomColor) -> Unit,
    onMonthTextColorChange: (CustomColor) -> Unit,
    onDaysTextColorChange: (CustomColor) -> Unit,
    onBoxLinesColorChange: (CustomColor) -> Unit,
    onSeparatorLinesColorChange: (CustomColor) -> Unit
) {

    LazyColumn(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        item {
            PropertyColor("Calendar Background", calendarBackground, onCalendarColorChange)
        }
        item {
            PropertyColor("Month Background", monthBackgroundColor, onMonthBackgroundColorChange)
        }
        item {
            PropertyColor("Month Text", monthTextColor, onMonthTextColorChange)
        }
        item {
            PropertyColor("Days Background", daysBackgroundColor, onDaysBackgroundColorChange)
        }
        item {
            PropertyColor("Days Text", daysTextColor, onDaysTextColorChange)
        }
        item {
            PropertyColor("Box Lines", boxLinesColor, onBoxLinesColorChange)
        }
        item {
            PropertyColor("Separator Lines", separatorLinesColor, onSeparatorLinesColorChange)
        }
    }
}

@Composable
private fun PropertyColor(name: String, color: CustomColor, onChange: (CustomColor) -> Unit) {
    var expand by remember { mutableStateOf(false) }
    Column(modifier = Modifier.width(300.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
            Text(text = name)
            Spacer(
                modifier = Modifier.padding(horizontal = 8.dp).height(2.dp).weight(1f)
                    .background(color = Color.LightGray)
            )
            IconButton(onClick = {
                expand = !expand
            }) {
                Icon(
                    imageVector = if (!expand) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                    contentDescription = null
                )
            }
        }
        AnimatedVisibility(expand, enter = fadeIn() + expandVertically(), exit = fadeOut() + shrinkVertically()) {
            ColorSelectTool(color, onChange)
        }
    }
}