package ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.CustomColor

@Composable
fun ColorSelectTool(color: CustomColor, onColorChange: (CustomColor) -> Unit) {

    Row(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            ColorSlider(
                propertyName = "Color",
                propertyRange = 0f..360f,
                sliderValue = color.hue,
                onValueChange = { onColorChange(color.copy(hue = it)) }
            )
            ColorSlider(
                propertyName = "Saturation",
                propertyRange = 0f..1f,
                sliderValue = color.saturation,
                onValueChange = { onColorChange(color.copy(saturation = it)) }
            )
            ColorSlider(
                propertyName = "Lightness",
                propertyRange = 0f..1f,
                sliderValue = color.lightness,
                onValueChange = { onColorChange(color.copy(lightness = it)) }
            )
        }
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(
                    color = Color.hsl(color.hue, color.saturation, color.lightness),
                    shape = CutCornerShape(topStart = 8.dp, bottomEnd = 8.dp)
                )
        ) { }
    }
}

@Composable
private fun ColorSlider(
    propertyName: String,
    propertyRange: ClosedFloatingPointRange<Float>,
    sliderValue: Float,
    onValueChange: (Float) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Slider(
            modifier = Modifier.width(180.dp),
            value = sliderValue,
            onValueChange = { onValueChange(it) },
            valueRange = propertyRange,
        )
        Text(text = propertyName)
    }
}
