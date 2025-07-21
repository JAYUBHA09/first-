package com.example.spleshscreen.PaySlip

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun YearPickerDialog(showDialog: Boolean ,
                     onDismiss: () -> Unit,
                     onYearSelected : (Int) -> Unit ,
                     selectedYear : Int) {
    if (showDialog) {

        Dialog(onDismissRequest = onDismiss) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)
                    .fillMaxWidth() ,
                    horizontalAlignment = Alignment.CenterHorizontally ,
                    verticalArrangement = Arrangement.Center) {

                    Text(text = "Select Year" ,
                        color = Color.Red,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    val currentYear = LocalDate.now().year
                    val yearList = (2001..currentYear).toList().reversed()
                    var tempSelectedYear by remember{mutableStateOf(selectedYear)}

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .height(350.dp),
                        horizontalAlignment = Alignment.CenterHorizontally ,
                        verticalArrangement = Arrangement.Center


                    ) {
                        items(yearList) { year ->

                            Text(
                                text = year.toString(),
                                color = if (tempSelectedYear == year)Blue else Color.Black,
                                fontWeight =if (tempSelectedYear == year) FontWeight.Bold else FontWeight.Normal ,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {
                                        tempSelectedYear = year
                                    }
                                    .align(Alignment.CenterHorizontally),
                                fontSize = 16.sp
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(15.dp))
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        Button(
                            onClick = { onDismiss()},
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF3846D)) ,
                            modifier = Modifier.size(width = 110.dp, height = 55.dp)
                                .background(color = Color(0xFFF3846D) , shape = RoundedCornerShape(10.dp))
                        ) {
                            Text("Cancel")
                        }
                        Button(
                            onClick = {
                                onYearSelected(tempSelectedYear)
                                onDismiss()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF51B951)) ,
                            modifier = Modifier.size(width = 110.dp, height = 55.dp)
                                .background(color = Color(0xFF51B951) , shape = RoundedCornerShape(10.dp))
                        )
                        {
                            Text("Ok")
                        }
                    }
                }

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)

@Composable
fun YearDialogPreview(){

    var  showYearDialog by remember { mutableStateOf(true) }
    var selectedYear by remember {mutableStateOf(2025)}

    YearPickerDialog(
        showDialog = showYearDialog,
        selectedYear = selectedYear,
        onDismiss = {showYearDialog = false} ,
        onYearSelected = { year -> selectedYear = year}
    )

}