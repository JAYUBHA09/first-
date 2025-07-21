package com.example.spleshscreen.TaskScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spleshscreen.Navigation.Screens
import com.example.spleshscreen.R

import com.example.spleshscreen.ui.theme.Blue
import com.example.spleshscreen.ui.theme.DarkBlue
import com.example.spleshscreen.ui.theme.DarkGreen
import com.example.spleshscreen.ui.theme.DarkRed
import com.example.spleshscreen.ui.theme.DarkYellow
import com.example.spleshscreen.ui.theme.LiteGray
import com.example.spleshscreen.ui.theme.LiteGreen
import com.example.spleshscreen.ui.theme.LiteRed
import com.example.spleshscreen.ui.theme.LiteYellow
import com.example.spleshscreen.ui.theme.NavyBlue
import com.example.spleshscreen.ui.theme.Orange
import com.example.spleshscreen.ui.theme.Purple80
import com.example.spleshscreen.ui.theme.PurpleGrey80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetail(navController: NavController  , viewModel: TaskViewModel) {

   val task = viewModel.selectedTask.value

    Column(modifier = Modifier.background(Blue)) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Task Detail",
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



            Column(modifier = Modifier.padding(16.dp)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = LiteGray, shape = RoundedCornerShape(10.dp))
                    )

                    {
                        Image(painter = painterResource(R.drawable.t), contentDescription = null)
                    }

                    Row {

                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .background(
                                    color = LiteRed
                                        ,
                                    shape = RoundedCornerShape(10.dp)
                                )
                        ) {
                            Text(
                                text = "High",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(top = 2.dp , bottom = 2.dp , start = 8.dp , end = 8.dp),
                                color = DarkRed


                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))

                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .background(
                                    color = LiteGreen
                                    ,
                                    shape = RoundedCornerShape(10.dp)
                                )
                        ) {
                            Text(
                                text = "Completed",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(top = 2.dp , bottom = 2.dp , start = 8.dp , end = 8.dp),
                                color = DarkGreen

                            )

                        }
                    }


                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Task Tracker", color = DarkBlue, fontWeight = FontWeight.ExtraBold)

                Spacer(modifier = Modifier.height(6.dp))

                // Task

                if (task != null) {
                    Text(text = task.task, color = Color.Gray)
                }

                Spacer(modifier = Modifier.height(15.dp))

                Text(text = "Task User", color = DarkBlue, fontWeight = FontWeight.ExtraBold)

                Spacer(modifier = Modifier.height(10.dp))


                Box(modifier = Modifier.padding(top = 16.dp)){

                    OverLapingRow()

                }


                Spacer(modifier = Modifier.height(10.dp))


                Card(
                    modifier = Modifier.padding(top = 16.dp),
                    colors = CardDefaults.cardColors(LiteGray),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color(0xFF3A65FF))
                ) {

                    Column() {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .background(color = Color(0xFFb3c3ff)),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                "Task Tracker",
                                color = NavyBlue,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(10.dp)
                            )

                            Spacer(Modifier.weight(1f))

                            Text(
                                "Hide",
                                color = Color(0xFF3A65FF),
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(10.dp)
                            )


                        }
                    }

                        TimeLineItem()
                    }


                }



            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {

                Box(
                    modifier = Modifier
                        .clickable(onClick = {navController.navigate(Screens.Comment.CommentScreen.route)})
                        .fillMaxWidth().height(50.dp)
                        .background(Orange)
                        ,
                    contentAlignment = Alignment.Center

                ) {
                    Text(text = "Comments" , color = Color.White , fontSize = 20.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
        }
}


@Composable
fun TaskDetailPreview(){

  //  TaskDetail(navController = rememberNavController())
}