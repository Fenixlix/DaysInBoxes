package ui_components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.CalendarDistribution

@Composable
fun CalendarScreenSizeControler(
    currentDistribution: CalendarDistribution,
    changeScreenSize: (CalendarDistribution) -> Unit
) {
    var showSizeOptionsDialog by remember { mutableStateOf(false) }
    val toggle = { showSizeOptionsDialog = !showSizeOptionsDialog }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedButton(onClick = {
            toggle()
        }) {
            Text("Change Distribution?")
        }
        Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
        Text(
            modifier = Modifier
                .width(80.dp)
                .border(width = 1.dp, shape = RoundedCornerShape(25), color = MaterialTheme.colors.primary)
                .padding(8.dp),
            text = currentDistribution.distributionCode,
            textAlign = TextAlign.Center
        )
    }

    if (showSizeOptionsDialog) {
        DistributionSelectDialog(
            onDismiss = { toggle() },
            onSizeSelect = {
                changeScreenSize(it)
                toggle()
            },
            selectedOne = currentDistribution.ordinal
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DistributionSelectDialog(
    onDismiss: () -> Unit,
    selectedOne: Int,
    onSizeSelect: (CalendarDistribution) -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Columns X Rows",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                for (option in CalendarDistribution.values()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = option.distributionCode
                        )
                        RadioButton(selected = selectedOne == option.ordinal, onClick = {
                            onSizeSelect(option)
                        })
                    }
                }
            }
        },
        buttons = {},
        dialogPadding = PaddingValues(all = 8.dp)
    )
}