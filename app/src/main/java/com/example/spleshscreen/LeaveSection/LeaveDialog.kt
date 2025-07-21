package com.example.spleshscreen.LeaveSection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.spleshscreen.ui.theme.Blue

@Composable
fun LeaveDialog(showDialog :Boolean,
    onDismiss: () -> Unit){



    if (showDialog){
       Dialog(onDismissRequest = onDismiss) {

           Surface(
               shape = RoundedCornerShape(8.dp),
               color = Color.White,
               modifier = Modifier.fillMaxWidth()
           ) {
               Column(modifier = Modifier.fillMaxWidth()
                   .padding(8.dp)) {

                   Row(modifier = Modifier.fillMaxWidth() ,
                       horizontalArrangement = Arrangement.SpaceBetween,
                       verticalAlignment = Alignment.CenterVertically) {

                       Text(text = "Sick Leave" , color = Blue , fontWeight = FontWeight.Bold , fontSize = 15.sp)

                       IconButton(onClick = { false.also { onDismiss() } }) {
                           Icon(
                               imageVector = Icons.Default.Close ,
                               contentDescription = "Close" ,
                               tint = Color.Red)
                       }
                   }
                   Row(modifier = Modifier.fillMaxWidth(),
                       verticalAlignment = Alignment.CenterVertically) {

                       Text(text = "Reason: \n  " , fontWeight = FontWeight.Bold)

                       Spacer(modifier = Modifier.width(3.dp))

                       Text(text = "Hello Madam, My health is not \nwell so today i am taking leave" ,
                           color = Color.Gray ,
                           fontSize = 15.sp)
                   }

                   Spacer(modifier = Modifier.height(4.dp))

                   Row(modifier = Modifier.fillMaxWidth(),
                       verticalAlignment = Alignment.CenterVertically){

                       Text(text = "1 :- " , fontWeight = FontWeight.Bold)

                       Spacer(modifier = Modifier.width(3.dp))

                       Text("Full Day", color = Color.Gray)

                   }
                   Spacer(modifier = Modifier.height(4.dp))

                   Row(modifier = Modifier.fillMaxWidth(),
                       verticalAlignment = Alignment.CenterVertically){

                       Text(text = "Total leave Taken Days :  " , fontWeight = FontWeight.Bold)

                       Spacer(modifier = Modifier.width(3.dp))

                       Text("1", color = Color.Gray)

                   }
                   Spacer(modifier = Modifier.height(4.dp))

                   Row(modifier = Modifier.fillMaxWidth(),
                       verticalAlignment = Alignment.CenterVertically){

                       Icon(
                           imageVector = Icons.Default.DateRange ,
                           contentDescription = "DateRange" ,
                           tint = Color.Red)

                       Spacer(modifier = Modifier.width(3.dp))

                       Text(text = "2025-06-18"+" " + "To" +" "+ "2025-06-18" , color = Color.Red)
                   }



               }
           }
       }
    }

}

@Composable
fun DialogPreview(){
    var  showDialog by remember { mutableStateOf(true) }
    LeaveDialog(
        showDialog = showDialog ,
        onDismiss = {showDialog = false}
    )
}