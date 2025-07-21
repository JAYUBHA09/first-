package com.example.spleshscreen.HomeScreen

import HomeGrid
import MainGrid
import android.R.attr.navigationIcon

import android.icu.text.SimpleDateFormat
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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButtonDefaults.elevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
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
import coil.size.Scale
import com.example.spleshscreen.Navigation.Screens
import com.example.spleshscreen.R
import com.example.spleshscreen.UserDetailApi.AuthViewModel
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
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        val prefs = UserPreferences(context)
        val token  = prefs.getToken()
        Log.d("Login", "New token: $token")
        Log.d("Prefs", "Stored token: ${prefs.getToken()}")
        viewModel.loadUserFromToken(token)
    }


    val user = viewModel.user

    LaunchedEffect(user) {
        Log.d("HomeScreen",  "user_id :${viewModel.user?.firstname}  ${viewModel.user?.user_id}")
    }


    Column(
        modifier = Modifier
            .padding(start = 12.dp , end = 12.dp)
            .fillMaxSize()
            .background(LiteGray)

    ) {

        TopAppBar( modifier = Modifier.padding(start = 10.dp , end = 10.dp , top = 4.dp),
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
                    .padding(start = 16.dp , end = 16.dp, top = 10.dp , bottom = 10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {

                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BadgedBox(
                        badge = {
                            Badge(containerColor = Color.Green)
                        }
                    ) {

                        Image(

                            painterResource(com.example.spleshscreen.R.drawable.account),
                            contentDescription = "Register Image",
                            modifier = Modifier
                                .clickable(onClick = {navController.navigate(Screens.MainScreen.ProfileDetail.route)})
                                .clip(CircleShape)
                                .width(55.dp)
                                .height(55.dp)
                                .border(width = 2.dp, color = Purple80, shape = CircleShape),
                            alignment = Alignment.TopEnd
                        )
                    }



                    Column(
                        modifier = Modifier.padding(10.dp)
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

                            viewModel.user?.let {
                                Text(
                                    it.firstname,
                                    style = TextStyle(fontWeight = FontWeight.Bold),
                                    color = NavyBlue
                                )
                            }
                        }


                        Spacer(modifier = Modifier.height(4.dp))

                        Text("Android Developer", color = PurpleGrey40)

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            currentDate,
                            style = TextStyle(fontWeight = FontWeight.Medium, color = DarkBlue)
                        )

                    }

                    Spacer(Modifier.weight(1f))

                    Column(modifier = Modifier.padding(12.dp)) {

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
                            Text("In", fontSize = 15.sp , fontWeight = FontWeight.Bold)
                        }
                    }


                }

            }
            MainGrid(navController)
            WeeklyGraph()
        }
    }
}

@Composable
fun HomeScreenPreview(){
    val navController = rememberNavController()
    val viewModel: AuthViewModel = viewModel()
    HomeScreen(navController , viewModel)
}




