package ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.DaysOfTheWeek

@Composable
fun CalendarMonth(
    monthName: String,
    weeks: List<List<Int?>>,
    externalBorderColor: Color = Color.Black,
    separatorLinesColor: Color = Color.Black,
    monthBackgroundColor: Color = Color.LightGray,
    daysBackgroundColor: Color = Color.LightGray,
    monthTextColor: Color = Color.Blue,
    daysTextColor: Color = Color.Blue
) {

    Column(
        modifier = Modifier
            .wrapContentSize()
            .border(width = 4.dp, color = externalBorderColor)
    ) {
        Text(
            modifier = Modifier
                .width(240.dp)
                .background(color = monthBackgroundColor)
                .padding(vertical = 4.dp),
            text = monthName,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = monthTextColor
        )

        Spacer(modifier = Modifier.height(4.dp).width(240.dp).background(color = separatorLinesColor))

        Row(
            modifier = Modifier
                .width(240.dp)
                .background(color = daysBackgroundColor)
                .padding(start = 4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DaysOfTheWeek.values().iterator().forEach {
                Text(
                    modifier = Modifier
                        .width(30.dp)
                        .padding(end = 4.dp, top = 2.dp, bottom = 2.dp),
                    text = it.shortName,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = daysTextColor
                )
            }
        }

        Spacer(modifier = Modifier.height(2.dp).width(240.dp).background(color = separatorLinesColor))

        weeks.forEach { week ->
            Row(
                modifier = Modifier
                    .width(240.dp)
                    .background(color = daysBackgroundColor)
                    .padding(start = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                week.forEach {
                    Text(
                        modifier = Modifier
                            .width(30.dp)
                            .padding(end = 4.dp, top = 4.dp, bottom = 4.dp),
                        text = it?.toString() ?: " ",
                        textAlign = TextAlign.Center,
                        color = daysTextColor
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}