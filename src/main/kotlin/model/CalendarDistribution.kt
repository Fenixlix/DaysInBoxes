package model

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

enum class CalendarDistribution(val size: DpSize, val distributionCode: String,val isVertical :Boolean ) {
    OneColumn(DpSize(264.dp, 800.dp), "1 X 12", true),
    TwoColumn(DpSize(520.dp, 800.dp), "2 X 6", true),
    ThreeColumn(DpSize(776.dp, 800.dp), "3 X 4", true),
    FourColumn(DpSize(1032.dp, 650.dp), "4 X 3", true),
    SixColumn(DpSize(1544.dp, 480.dp), "6 X 2", false),
    TwelveColumn(DpSize(1544.dp, 250.dp), "12 X 1", false)
}

