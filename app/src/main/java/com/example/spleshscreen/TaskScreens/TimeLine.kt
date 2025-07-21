package com.example.spleshscreen.TaskScreens

import android.R.attr.radius
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spleshscreen.ui.theme.Blue
import com.example.spleshscreen.ui.theme.LiteGray
import com.example.spleshscreen.ui.theme.NavyBlue
import com.example.spleshscreen.ui.theme.Purple80

@Composable
fun TimeLien( title: String,
              date: String,
              isCompleted: Boolean,
              isLast: Boolean){




            Row(modifier = Modifier.padding(start = 16.dp , bottom = 8.dp)) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Canvas(modifier = Modifier.size(24.dp)) {

                        drawCircle(
                            color = if(isCompleted) Blue else Color.Gray ,
                            style = Stroke(width = 3.dp.toPx())
                        )
                        if(isCompleted){
                            drawCircle(
                                color = Blue,
                                radius = 6.dp.toPx()
                            )
                        }

                    }
                    if(!isLast){
                        Spacer(modifier = Modifier.height(4.dp))

                        Box(modifier = Modifier
                            .height(30.dp)
                            .width(2.dp)
                            .background(if(isCompleted) Blue else Color.Gray)
                        )
                    }
                }


                Spacer(modifier = Modifier.width(10.dp))

                Column{
                    Text(text = title , fontWeight = FontWeight.Medium)
                    Text(text = date , fontWeight = FontWeight.Bold , color = NavyBlue)


            }


            }

        }



@Composable
fun TimeLineItem() {
    Column(modifier = Modifier
        .padding(vertical = 16.dp)
        .background(LiteGray)) {


        TimeLien("Start Date", "May 10, 2021", isCompleted = true, isLast = false)
        TimeLien("End Date", "May 15, 2021", isCompleted = true, isLast = false)
        TimeLien("Deadline Date", "May 15, 2021", isCompleted = true, isLast = false)
        TimeLien("Delivery Date", "May 19, 2021", isCompleted = false, isLast = false)
        TimeLien("Testing Date", "May 20 2021", isCompleted = false, isLast = true)
    }
}