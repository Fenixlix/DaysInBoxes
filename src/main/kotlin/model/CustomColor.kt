package model

import androidx.compose.ui.graphics.Color

data class CustomColor(
    var hue: Float = 0f,
    var saturation: Float = 1f,
    var lightness: Float = 0.5f
)

fun CustomColor.toColor() = Color.hsl(hue = this.hue, saturation = this.saturation, lightness = this.lightness)