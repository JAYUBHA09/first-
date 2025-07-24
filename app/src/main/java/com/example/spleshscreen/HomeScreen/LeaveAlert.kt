package com.example.spleshscreen.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.spleshscreen.ui.theme.NavyBlue

@Composable
fun LeaveAlert(
    ShowDialog : MutableState<Boolean>
){

if (ShowDialog.value) {
    Dialog(onDismissRequest = { ShowDialog.value = false}) {

        Box(
            modifier = Modifier
                .size(width = 370.dp, height = 277.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color.White),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Alert!",
                    color = Color(0xFF38365E),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(modifier = Modifier.padding(6.dp))

                Text(
                    text = "Your Total Working Time is",
                    color = NavyBlue,
                    modifier = Modifier,
                    fontWeight = FontWeight.Normal
                )

                Row {
                    Text(
                        text = "07:50 ",
                        color = Color.Red,
                        modifier = Modifier,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "Are you leaving the",
                        color = NavyBlue,
                        modifier = Modifier,
                        fontWeight = FontWeight.Normal
                    )
                }

                Text(
                    text = "office? Or taking a break? ",
                    color = NavyBlue,
                    modifier = Modifier,
                    fontWeight = FontWeight.Normal
                )


                Spacer(modifier = Modifier.padding(16.dp))



                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = { ShowDialog.value = false},
                        modifier = Modifier
                            .size(width = 144.dp, height = 55.dp)
                            .padding(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color(0xFFF3846D)
                        ),
                        shape = RoundedCornerShape(12.dp)

                    ) {
                        Text("Day End")
                    }

                    Button(
                        onClick = {ShowDialog.value = false },
                        modifier = Modifier
                            .size(width = 144.dp, height = 55.dp)
                            .padding(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color(0xFF51B951)
                        ),
                        shape = RoundedCornerShape(12.dp)

                    ) {
                        Text("Break")
                    }


                }


            }
        }
    }
}


}





