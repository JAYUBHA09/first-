package com.example.spleshscreen.HomeScreen

import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Hour(
    val day : String,
    val hour : Float
)

@Composable
fun DrawGraph(data : List<Hour>){

    Card(modifier = Modifier
        .padding(start = 12.dp , end = 12.dp , top = 8.dp , bottom = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp) ) {

        Column(
            modifier = Modifier
                .background(Color(0xFFF5F7FA),
                    RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Weekly Graph",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold)

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Legend(Color(0xFFFF9800), "Half Day")
                    Legend(Color(0xFF2962FF), "Full Day")
                }

            }


            Spacer(modifier = Modifier.height(12.dp))


            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {

                val maxHour = 8f
                val barWidth = size.width / (data.size * 2)
                val spacing = barWidth
                val maxHeight = size.height * 0.8f
                val bottomPadding = size.height * 0.2f

                // Grid
                val gridLine = 4
                val gridStep = maxHeight / gridLine
                val textPaint = android.graphics.Paint().apply {
                    textSize = 24f
                    color = android.graphics.Color.DKGRAY
                }

                for (i in 0..gridLine) {

                    val y = size.height - bottomPadding - (i * gridStep)

                    drawLine(
                        color = Color.LightGray,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = 1.dp.toPx()

                    )
                    drawContext.canvas.nativeCanvas.drawText(
                        "${i * 2}h",
                        0f,
                        y - 4,
                        textPaint
                    )
                }

                // Bars

                data.forEachIndexed { index, item ->

                    val barHeight = (item.hour / maxHour) * maxHeight
                    val barColor = if (item.hour < 8) Color(0xFFFF9800) else Color(0xFF2962FF)

                    val x = index * (barWidth + spacing) + spacing / 2
                    val y = size.height - bottomPadding - barHeight

                    drawRoundRect(
                        color = barColor,
                        topLeft = Offset(x, y),
                        size = Size(barWidth, barHeight),
                        cornerRadius = CornerRadius(10f)
                    )

                    // Day Label

                    drawContext.canvas.nativeCanvas.drawText(
                        item.day,
                        x + barWidth / 4,
                        size.height - 50f,
                        android.graphics.Paint().apply {
                            textSize = 28f
                            color = android.graphics.Color.BLACK
                            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                        }

                    )

                }

            }
        }
    }
}


@Composable
fun WeeklyGraph(){
    val list = listOf(

        Hour("Mon" , 6f),
        Hour("Tue" , 8f),
        Hour("Wed" , 8f),
        Hour("Thu" , 8f),
        Hour("Fri" , 8f),
        Hour("Sat" , 1f),
        Hour("Sun" , 1f)

    )

    DrawGraph(data = list)
}

@Composable
fun Legend(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Canvas(modifier = Modifier.size(10.dp)) {
            drawCircle(color)
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(label, fontSize = 12.sp)
    }
}