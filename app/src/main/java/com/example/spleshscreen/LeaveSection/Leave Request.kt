package com.example.spleshscreen.LeaveSection

import java.util.Calendar
import android.app.DatePickerDialog
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spleshscreen.ui.theme.Blue
import com.example.spleshscreen.ui.theme.LiteGray
import com.example.spleshscreen.ui.theme.Orange
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun LeaveRequest(navController: NavController) {


    var selectedIndex by remember { mutableStateOf(0) }
    val selectDay = arrayOf("Full Day", "Half Day")

    val leaveType = listOf("Paid Leave", "Sick Leave")
    var expanded  by remember{mutableStateOf(false)}
    var selectedText by remember { mutableStateOf("") }
    var reason by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Blue)
    )
    {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Leave Request",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = {navController.navigateUp() }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Blue)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    LiteGray,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .padding(top = 16.dp)
        ) {

            Column(modifier = Modifier.padding(start = 12.dp , end = 12.dp)) {

                Box {
                    Column(modifier = Modifier.padding(10.dp)) {

                        Text(text = "Plese Select leave type", color = Color.Gray)

                        Spacer(modifier = Modifier.height(8.dp))

                        ExposedDropdownMenuBox(
                            expanded = expanded ,
                            onExpandedChange = {expanded = !expanded }
                        ) {

                            TextField(
                                value = selectedText,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text("Leave Type") },
                                trailingIcon = {
                                    Icon(
                                        if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Dropdown"
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor(),
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent,
                                    disabledContainerColor = Color.Transparent
                                )
                            )
                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = {expanded = false}
                            ){
                                leaveType.forEach{ item -> 
                                    DropdownMenuItem(
                                        text = { Text(text = item)} ,
                                        onClick = { selectedText = item
                                        expanded = false}

                                    )
                                    
                                }
                            }

                        }
                    }
                }
                val currentDate = remember {
                    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
                }

                val context = LocalContext.current
                val calendar = Calendar.getInstance()

                var startDate by remember { mutableStateOf(currentDate) }
                var endDate by remember { mutableStateOf(currentDate) }

// Start DatePickerDialog

                val startDatePickerDialog = remember {
                    DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            startDate = "%02d/%02d/%d".format(dayOfMonth, month + 1, year)
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).apply {
                        datePicker.minDate = calendar.timeInMillis
                    }
                }

// End DatePickerDialog

                val endDatePickerDialog = remember {
                    DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            endDate = "%02d/%02d/%d".format(dayOfMonth, month + 1, year)
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).apply {
                        datePicker.minDate = calendar.timeInMillis
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Column(modifier= Modifier.padding(10.dp)) {

                        Text(text = "Please select start date", color = Color.Gray)

                        Spacer(modifier = Modifier.height(8.dp))

                        Card(
                            modifier = Modifier
                                .height(40.dp)
                                .width(160.dp)
                                .clickable{endDatePickerDialog.show()},
                            elevation = CardDefaults.cardElevation(10.dp),
                            colors = CardDefaults.cardColors(Color.White),
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(4.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = null,
                                    tint = Orange
                                )
                                Spacer(modifier = Modifier.width(4.dp))

                                Text(text = endDate, fontWeight = FontWeight.Bold)

                            }
                        }



                    }

                    Column(modifier = Modifier.padding(10.dp)
                        ,horizontalAlignment = Alignment.CenterHorizontally) {
                        
                        Text(text = "Please select end date", color = Color.Gray)

                        Spacer(modifier = Modifier.height(8.dp))

                        Card(
                            modifier = Modifier
                                .height(40.dp)
                                .width(160.dp)
                                .clickable{startDatePickerDialog.show()},
                            elevation = CardDefaults.cardElevation(10.dp),
                            colors = CardDefaults.cardColors(Color.White)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(4.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = null,
                                    tint = Orange
                                )
                                Spacer(modifier = Modifier.width(4.dp))

                                Text(text = startDate, fontWeight = FontWeight.Bold)

                            }
                        }


                    }
                }



                Column(modifier = Modifier.padding(10.dp)) {

                Text(text = "Please select day", color = Color.Gray)

                Spacer(modifier = Modifier.height(8.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = CardDefaults.cardColors(Color.White),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {

                    Row(modifier = Modifier.fillMaxSize()) {
                        selectDay.forEachIndexed { index, day ->
                            Card(
                                onClick = { selectedIndex = index },
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f)
                                    .padding(6.dp),
                                colors = CardDefaults.cardColors(containerColor = if (index == selectedIndex) Blue else LiteGray)
                            ) {

                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(6.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = selectDay[index].toString(),
                                        fontWeight = FontWeight.Bold,
                                        color = if (index == selectedIndex) Color.White else Color.Black
                                    )
                                }

                            }


                        }


                    }
                }
                    Spacer(modifier = Modifier.height(10.dp))


                    var selectedhalf by remember { mutableStateOf(0) }
                    val half = arrayOf("First Half", "Second Half")

                    AnimatedVisibility(visible = selectedIndex == 1) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            colors = CardDefaults.cardColors(Color.White),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {

                            Row(modifier = Modifier.fillMaxSize()) {
                                half.forEachIndexed { Hindex, half ->
                                    Card(
                                        onClick = { selectedhalf = Hindex },
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .weight(1f)
                                            .padding(6.dp),
                                        colors = CardDefaults.cardColors(containerColor = if (Hindex == selectedhalf) Blue else LiteGray)
                                    ) {

                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(6.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = half,
                                                fontWeight = FontWeight.Bold,
                                                color = if (Hindex == selectedhalf) Color.White else Color.Black
                                            )
                                        }

                                    }


                                }


                            }
                        }



                    }

                }



                Column(modifier = Modifier.padding(10.dp)) {

                    Text(text = "Please enter your reason" , color = Color.Gray)

                    Spacer(modifier = Modifier.height(8.dp))

                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp) ,
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.cardElevation(10.dp)) {

                        OutlinedTextField(value = reason,
                            onValueChange = {reason = it} ,
                            label = {Text("Reason" , fontWeight = FontWeight.Medium , color = Color.DarkGray)} ,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent ,

                            ),
                            modifier = Modifier
                                .fillMaxSize()
                        )


                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = {},
                        modifier = Modifier.fillMaxWidth()
                            .height(50.dp)
                            .background(color = Orange , shape = RoundedCornerShape(8.dp))
                            .clip(RoundedCornerShape(6.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Orange, contentColor = Color.White)) {

                        Text(text = "Submit Request" , fontWeight = FontWeight.Bold )
                    }
                }


            }
        }
    }
}

@Composable
fun Perview()
{
    val navController = rememberNavController()

    LeaveRequest(navController)
}


