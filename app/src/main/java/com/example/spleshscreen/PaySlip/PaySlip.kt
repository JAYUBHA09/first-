package com.example.spleshscreen.PaySlip

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.spleshscreen.Navigation.Screens
import com.example.spleshscreen.ui.theme.Blue
import com.example.spleshscreen.ui.theme.LiteGray
import com.example.spleshscreen.ui.theme.NavyBlue
import com.example.spleshscreen.ui.theme.Orange
import com.google.android.material.chip.Chip

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun PaySlip(navController: NavController) {

    var  showYearDialog by remember { mutableStateOf(false) }
    var selectedYear by remember {mutableStateOf(2025)}

    YearPickerDialog(
        showDialog = showYearDialog,
        selectedYear = selectedYear,
        onDismiss = {showYearDialog = false} ,
        onYearSelected = { year -> selectedYear = year}
    )

    Column(modifier = Modifier.background(Blue)) {

        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Pay Slip",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
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
                .background(
                    color = LiteGray,
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                )
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Recent",
                    color = NavyBlue,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )


                Box(
                    modifier = Modifier
                        .size(width = 97.dp, height = 39.dp)
                        .clickable { showYearDialog = true }
                        .border(
                            width = 1.dp,
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            selectedYear.toString(), fontWeight = FontWeight.Bold,
                            modifier = Modifier.size(width = 44.dp, height = 22.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Arrow Down",
                            tint = Blue,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(6.dp))

            SlipList()


        }
    }


}

data class Slip(var month: String ,var date: String)


@Composable
fun SlipList() {

    val slipList = listOf<Slip>(
        Slip("Jan", "04/05/2002"),
        Slip("Feb", "04/05/2002"),
        Slip("March", "04/05/2002"),
        Slip("April", "04/05/2002"),
        Slip("April", "04/05/2002"),
        Slip("May", "04/05/2002"),
        Slip("June", "04/05/2002"),
        Slip("July", "04/05/2002"),
        Slip("Aug", "04/05/2002"),
        Slip("Sep", "04/05/2002"),
        Slip("Oct", "04/05/2002"),
        Slip("Nov", "04/05/2002"),
        Slip("Dec", "04/05/2002")
    )

    LazyColumn {
        item {

            slipList.forEach() { slip ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp , end = 30.dp , top = 10.dp, bottom = 10.dp)
                        .height(100.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(10.dp),
                    colors = CardDefaults.cardColors(Color.White)
                ) {

                    Row( modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween ,
                        verticalAlignment = Alignment.CenterVertically) {

                        Column(modifier = Modifier.padding(start = 10.dp , end = 10.dp , top = 4.dp, bottom = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally)
                        {
                            Text(text = "Salary" , fontWeight = FontWeight.SemiBold)

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = slip.month,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Blue
                            )
                        }

                        Column(modifier = Modifier.padding(start = 10.dp , end = 10.dp , top = 4.dp, bottom = 4.dp)
                            ,horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = slip.date , fontWeight = FontWeight.Normal )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(text = "Detail>" , color = Orange)

                        }


                    }

                }

            }

        }
    }
}