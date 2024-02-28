package ui_components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.CalendarDistribution

@Composable
fun UserControls(
    calendarWindowSize: CalendarDistribution,
    showColorToolkit: Boolean,
    changeCalendarSize: (CalendarDistribution) -> Unit,
    toggleShowColorToolkit: () -> Unit
) {

    var expand by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.height(2.dp).background(color = Color.LightGray).weight(1f))
        Row(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(25))
                .clickable { expand = !expand },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Edition options and tools"
            )
            ToggableDropDownIcon(expand)
        }
        Spacer(modifier = Modifier.height(2.dp).background(color = Color.LightGray).weight(1f))
    }

    AnimatedVisibility(expand, enter = fadeIn() + expandVertically(), exit = fadeOut() + shrinkVertically()) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            OutlinedButton(onClick = { toggleShowColorToolkit() }) {
                if (showColorToolkit) {
                    Text("Hide Color Toolkit")
                } else {
                    Text("Show Color Toolkit")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            CalendarScreenSizeControler(calendarWindowSize, changeScreenSize = { changeCalendarSize(it) })
        }
    }
}