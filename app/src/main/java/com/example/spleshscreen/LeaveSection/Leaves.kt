package com.example.spleshscreen.LeaveSection

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.spleshscreen.Navigation.Screens
import com.example.spleshscreen.ui.theme.Blue
import com.example.spleshscreen.ui.theme.LiteGray
import com.example.spleshscreen.ui.theme.NavyBlue
import com.example.spleshscreen.ui.theme.Orange
import java.time.LocalDate
import java.time.Month
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Leave(navController: NavController , viewModel: LeaveViewModel){



    var selectedStatus by remember { mutableStateOf(LeaveStatus.APPROVED) }
    val context = LocalContext.current
    var selectedDate by remember {mutableStateOf(LocalDate.now())}
    val fullMonth = Month.of(selectedDate.monthValue).getDisplayName(java.time.format.TextStyle.FULL, Locale.ENGLISH)
    val displayMonth = if (fullMonth.length == 4) fullMonth else fullMonth.take(3)

    val leavesForDate = viewModel.getLeavesByDate(selectedDate)
    val approvedCount = leavesForDate.count { it.status == LeaveStatus.APPROVED }
    val pendingCount = leavesForDate.count { it.status == LeaveStatus.PENDING }

    val datePickerDialog = remember{
        val today = LocalDate.now()
        DatePickerDialog(
            context,
            {_, year, month, dayOfMonth ->
                selectedDate = LocalDate.of(year,month +1, dayOfMonth)
            },
            today.year,
            today.monthValue-1,
            today.dayOfMonth
        ).apply{
            datePicker.maxDate = System.currentTimeMillis()
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Blue)

    ) {

        CenterAlignedTopAppBar(
            title ={
                Text(
                    text = "Leaves", style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ), modifier = Modifier
                        .padding(20.dp)
                )
            } ,
            navigationIcon = {
                IconButton(onClick = {navController.navigateUp()}) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White

                    )
                }
            } ,
            actions = {
                IconButton(onClick = {navController.navigate(Screens.LeaveRequest.route)}) {

                    Image(painterResource(id = com.example.spleshscreen.R.drawable.add_box ) ,
                        contentDescription = "add" ,
                        modifier = Modifier.size(50.dp))
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Blue)

        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .background(
                    color = LiteGray,
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                )
        ) {
            Column(modifier = Modifier.padding(8.dp)) {



                // Toggle button


                LeavePage(
                    onFilterSelected = { selectedStatus = it },
                    approvedCount = approvedCount,
                    pendingCount = pendingCount
                )

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 16.dp , end = 16.dp, top = 10.dp , bottom = 10.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = displayMonth, style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = NavyBlue
                    ), modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterVertically)
                )

                Icon(
                    Icons.Default.DateRange,
                    contentDescription = "DatePicker",
                    tint = Orange,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.Bottom)
                        .clickable { datePickerDialog.show() }
                )

            }


                leavesList(selectedDate, selectedStatus, viewModel)
        }
        }
    }


}




@Composable
fun LeavePage( approvedCount: Int,
               pendingCount: Int ,
               onFilterSelected: (LeaveStatus) -> Unit){

    var selectedIndex by remember { mutableIntStateOf(0) }

    val leaves = listOf(
        Leave(approvedCount , "Approved Leave" , Color.Red  ) ,
        Leave(pendingCount, "Pending Leave" , Color.Green)
    )

    Card(
        modifier = Modifier
            .size(width = 550.dp, height = 100.dp)
            .padding(start = 16.dp , end = 16.dp, top = 10.dp , bottom = 10.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize() ){

            leaves.forEachIndexed {index, leave ->

                Card(onClick = {  selectedIndex = index
                    val selectedLeave = if(index == 0 ) LeaveStatus.APPROVED else LeaveStatus.PENDING
                    onFilterSelected(selectedLeave) },
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(6.dp),
                    colors = CardDefaults.cardColors(containerColor = if(index == selectedIndex)Blue else LiteGray)
                ) {

                        Box(modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp),
                            contentAlignment = Alignment.Center){

                            Column(horizontalAlignment = Alignment.CenterHorizontally ,
                                verticalArrangement = Arrangement.Center) {

                                Text(
                            text = leave.leave.toString(),
                            color = if (index == selectedIndex) Color.White else leave.color,
                            fontWeight = FontWeight.ExtraBold ,
                            fontSize = 20.sp
                        )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = leave.which ,
                                color = if (index== selectedIndex) Color.White else Color.Black
                            )


                        }
                    }
            }
            }
        }
    }
}




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Leaves(list:  List<LeaveList> ) {

    val today = LocalDate.now()
    val fullMonth = Month.of(today.monthValue).getDisplayName(java.time.format.TextStyle.FULL, Locale.ENGLISH)
    val displayMonth = if (fullMonth.length == 4) fullMonth else fullMonth.take(3)
    var  showDialog by remember { mutableStateOf(false) }

    LeaveDialog(
        showDialog = showDialog,
        onDismiss = {showDialog = false}
    )


    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier.background(LiteGray),

        horizontalArrangement = Arrangement.Center
    ) {
        items(list) { item ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp)
                    .padding(start = 16.dp , end = 16.dp, top = 10.dp , bottom = 10.dp)
                    .clickable { showDialog = true }
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    ),  colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(8.dp)

                ) {

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically

                    ) {

                    Box(
                        modifier = Modifier
                            .size(width = 55.dp, height = 90.dp)
                            .background(color = Color(0xFFc7d3ff), shape = RoundedCornerShape(8.dp))
                            ,
                        contentAlignment = Alignment.Center
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        )
                        {
                            Text(text = displayMonth, color = NavyBlue)
                            Text(
                                text = today.dayOfMonth.toString(),
                                color = Blue,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Column {
                        Text(text = item.type , color = Blue , fontWeight = FontWeight.Bold )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(text = item.reason , color = Color.Gray)

                        Spacer(modifier = Modifier.height(4.dp))


                            Text(text = "Detail>" ,
                                color = Orange ,
                                style = TextStyle(textDecoration = TextDecoration.Underline))
                    }
                }
            }

        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun leavesList(selectedDate: LocalDate,
               selectedStatus: LeaveStatus,
               viewModel: LeaveViewModel
){

    val leaves by viewModel.leaves.collectAsState()
    val filteredLeaves = leaves.filter { it.date == selectedDate && it.status == selectedStatus }
    Leaves(list = filteredLeaves)

}


