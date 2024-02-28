package ui_components

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable

@Composable
fun ToggableDropDownIcon(expand: Boolean) {
    Icon(
        imageVector = if (!expand) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
        contentDescription = null
    )
}