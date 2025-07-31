package com.example.spleshscreen.HomeScreen

import HomeGrid
import MainGrid
import android.R.attr.navigationIcon

import android.icu.text.SimpleDateFormat
import android.text.Layout
import android.text.Layout.Alignment.ALIGN_CENTER
import android.util.Log
import android.widget.Scroller

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.size.Scale
import coil.size.Scale.FILL
import com.example.spleshscreen.Navigation.Screens
import com.example.spleshscreen.R
import com.example.spleshscreen.UserDetailApi.AuthViewModel
import com.example.spleshscreen.UserDetailApi.UserDetails
import com.example.spleshscreen.UserDetailApi.UserPreferences
import com.example.spleshscreen.ui.theme.Blue
import com.example.spleshscreen.ui.theme.DarkBlue
import com.example.spleshscreen.ui.theme.LiteGray
import com.example.spleshscreen.ui.theme.NavyBlue
import com.example.spleshscreen.ui.theme.Orange
import com.example.spleshscreen.ui.theme.Purple80
import com.example.spleshscreen.ui.theme.PurpleGrey40
import kotlinx.coroutines.flow.firstOrNull


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun HomeScreen(navController: NavController , viewModel: AuthViewModel){


    val ShowDialog = remember{ mutableStateOf(false) }
    LeaveAlert(ShowDialog = ShowDialog)

    val scrollState = rememberScrollState()

    val sdf = SimpleDateFormat("dd MMM,yyyy")
    val currentDate = sdf.format(System.currentTimeMillis())
    val ct  = SimpleDateFormat("HH:mm:ss")
    val currentTime = ct.format(System.currentTimeMillis())
    val context = LocalContext.current
    val prefs = UserPreferences(context)
    val isLoading = viewModel.isLoading


    LaunchedEffect(Unit) {
       viewModel.fetchUserDetails(prefs)
    }
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.size(50.dp), color = Blue)
        }
    } else {

        Column(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .fillMaxSize()
                .background(LiteGray)

        ) {

            TopAppBar(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 4.dp),
                title = {
                    Text(
                        text = "Dashboard", style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = NavyBlue
                        ), modifier = Modifier

                    )
                },
                actions = {

                    Image(
                        painterResource(R.drawable.fingerprint), contentDescription = null,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(45.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(onClick = { navController.navigate(Screens.MainScreen.QrScanScreen.route) })
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = LiteGray)

            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ) {


                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    )
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp, top = 10.dp, bottom = 10.dp),
                        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BadgedBox(
                            badge = {
                                Badge(containerColor = Color.Green)
                            }
                        ) {

                            Image(

                                rememberAsyncImagePainter(viewModel.userDetails?.picture),
                                contentDescription = "Register Image",
                                modifier = Modifier
                                    .clickable(onClick = { navController.navigate(Screens.MainScreen.ProfileDetail.route) })
                                    .clip(CircleShape)
                                    .width(65.dp)
                                    .height(65.dp)
                                    .border(
                                        width = 1.dp,
                                        color = Color.DarkGray,
                                        shape = CircleShape
                                    ),
                                alignment = Alignment.Center,
                            )
                        }

                        Column(
                            modifier = Modifier.padding(4.dp)
                                .width(150.dp)
                        ) {
                            Row {
                                Text(
                                    "Hi!",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = NavyBlue
                                    )
                                )

                                Spacer(modifier = Modifier.width(4.dp))

                                viewModel.userDetails?.let { user ->
                                    Text(
                                        user.name,
                                        style = TextStyle(fontWeight = FontWeight.Bold),
                                        color = NavyBlue
                                    )
                                }
                            }


                            Spacer(modifier = Modifier.height(4.dp))

                            viewModel.userDetails?.designation_name?.let {
                                Text(
                                    it,
                                    color = PurpleGrey40
                                )
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                currentDate,
                                style = TextStyle(fontWeight = FontWeight.Medium, color = DarkBlue)
                            )

                        }

                        Spacer(Modifier.width(2.dp))

                        Column() {

                            Text(
                                currentTime,
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                style = TextStyle(fontWeight = FontWeight.Bold, color = DarkBlue)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White,
                                    containerColor = Orange
                                ),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("Out", color = Color.White)
                            }
                        }
                    }
                }
                MainGrid(navController)
                WeeklyGraph()
            }
        }
    }
}

@Composable
fun HomeScreenPreview(){
    val navController = rememberNavController()
    val viewModel: AuthViewModel = viewModel()
    HomeScreen(navController , viewModel)
}




