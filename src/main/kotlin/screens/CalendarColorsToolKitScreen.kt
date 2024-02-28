package screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.CustomColor
import ui_components.ColorSelectTool
import ui_components.ToggableDropDownIcon

@Composable
fun CaledarColorsToolKitScreen(
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
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            ColorProperty("Calendar Background", calendarBackground, onCalendarColorChange)
        }
        item {
            ColorProperty("Month Background", monthBackgroundColor, onMonthBackgroundColorChange)
        }
        item {
            ColorProperty("Month Text", monthTextColor, onMonthTextColorChange)
        }
        item {
            ColorProperty("Days Background", daysBackgroundColor, onDaysBackgroundColorChange)
        }
        item {
            ColorProperty("Days Text", daysTextColor, onDaysTextColorChange)
        }
        item {
            ColorProperty("Box Lines", boxLinesColor, onBoxLinesColorChange)
        }
        item {
            ColorProperty("Separator Lines", separatorLinesColor, onSeparatorLinesColorChange)
        }
    }
}

@Composable
private fun ColorProperty(name: String, color: CustomColor, onChange: (CustomColor) -> Unit) {
    var expand by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(25))
                .clickable { expand = !expand },
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(modifier = Modifier.padding(8.dp), text = name)
            Spacer(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .height(2.dp)
                    .weight(1f)
                    .background(color = Color.LightGray)
            )
            ToggableDropDownIcon(expand)
        }
        AnimatedVisibility(expand, enter = fadeIn() + expandVertically(), exit = fadeOut() + shrinkVertically()) {
            ColorSelectTool(color, onChange)
        }
    }
}