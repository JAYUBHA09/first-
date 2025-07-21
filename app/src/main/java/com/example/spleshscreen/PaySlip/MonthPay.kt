package com.example.spleshscreen.PaySlip

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.spleshscreen.ui.theme.Blue
import com.example.spleshscreen.ui.theme.LiteGray
import com.example.spleshscreen.ui.theme.NavyBlue
import java.time.Month

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Months(navController: NavController){


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
                IconButton(onClick = {navController.navigateUp()}) {
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
                    color = LiteGray,
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                )
        ) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) ,
                horizontalArrangement = Arrangement.SpaceBetween) {

                Text(
                    text = "Recent",
                    color = NavyBlue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Box(
                    modifier = Modifier.size(width = 97.dp, height = 39.dp)
                        .clickable{}
                        .border(
                            width = 1.dp,
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(8.dp)
                        ) ,
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Year" , fontWeight = FontWeight.Normal ,
                            modifier = Modifier.size(width = 44.dp , height = 22.dp))
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Arrow Down",
                            tint = Blue,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                }


            }

            val months = listOf(
                "Jan", "Feb", "March",
                "April", "May", "June",
                "July", "Aug", "Sep",
                "Oct", "Nov", "Dec"
            )

            LazyVerticalGrid(columns = GridCells.Fixed(3)) {

                items(months.size) { index ->

                    Card(modifier = Modifier
                        .clickable(onClick = {})
                        .size(width = 110.dp, height = 60.dp)
                        .padding(12.dp),
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.cardElevation(8.dp)
                        ) {

                        Text(months[index] ,
                            fontWeight = FontWeight.Bold ,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentHeight(Alignment.CenterVertically)) }
                    }


                }
            }
        }


}



@Composable
fun SuggestionChipExample() {
    SuggestionChip(
        onClick = { },
        label = { Text("Suggestion chip") }
    )
}







