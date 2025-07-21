package com.example.spleshscreen.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.spleshscreen.R
import com.example.spleshscreen.ui.theme.LiteGray
import com.example.spleshscreen.ui.theme.Purple80

@Composable
fun ProfileDialog(ShowDialog: MutableState<Boolean>){

    if (ShowDialog.value) {

    Dialog(onDismissRequest = { ShowDialog.value = false }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){


        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(Purple80)
        ) {


            Box(
                modifier = Modifier.padding(top = 60.dp),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .offset(y = (-50.dp))
                            .clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape)
                            .background(LiteGray)
                    ) {

                        Image(

                            painterResource(R.drawable.account),
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )


                    }


                    Text(text = "Harry Smith", fontWeight = FontWeight.ExtraBold, fontSize = 15.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "smith@gmail.com", color = Color.Gray)

                    Spacer(modifier = Modifier.height(6.dp))

                    InfoRow("Designation" , "PHP Developer")
                    Spacer(modifier = Modifier.height(2.dp))
                    InfoRow("Working Projects", "Welding Website,\n Employee Portal")
                    Spacer(modifier = Modifier.height(2.dp))
                    InfoRow("Total Working Hrs.", "41 hrs. 25 mins")
                }
            }
        }


        }
    }
}
    }

@Composable
fun InfoRow( lable : String ,  value : String){

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp) ,
        horizontalArrangement = Arrangement.Center
        ) {
        Text(text = "$lable  :" , fontSize = 10.sp)

        Spacer(modifier = Modifier.width(4.dp))

        Text(text = "$value", fontWeight = FontWeight.Medium)

    }

}



@Composable
fun Prev() {

    val ShowDialog = remember { mutableStateOf(true) }
   ProfileDialog(ShowDialog)
}