package com.example.spleshscreen.uiProject.splesh.Intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spleshscreen.R
import com.example.spleshscreen.Navigation.Screens
import com.example.spleshscreen.ui.theme.Blue
import com.example.spleshscreen.ui.theme.Orange
import com.example.spleshscreen.UserDetailApi.UserPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


data class OnBoardModel(
    val imageRes: Int,
    val title: String,
    val description: String
)


@Composable
fun InfoScreen( navController: NavController){
    val context = LocalContext.current
    val prefs = remember { UserPreferences(context) }

    Column {

        Image(
            painter = painterResource(id = R.drawable.splesh),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(385.dp)
        )

        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                Blue,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
            ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(25.dp),
            ) {


                Text(
                    text = ("Keep Healthy \nwork - life balance"),
                    style = TextStyle(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.ExtraBold ,
                        color = Color.White),
                    modifier = Modifier.padding(top = 20.dp)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = ("Streamline your workforce with our intuitive app."  +
                            "Designed to simplify task, track performance, and enhance communication"),
                    modifier = Modifier
                        .padding(6.dp),
                    fontWeight = FontWeight.Medium,
                    color = Color.LightGray,
                )



                Column (modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally

                
                ){


                    Button(onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            prefs.setFirstLaunch()
                        }

                        navController.navigate(Screens.AuthScreen.LoginScreen.route) {
                            popUpTo(Screens.InfoScreen.route) { inclusive = true }
                        }
                    },
                        modifier = Modifier
                            .padding(bottom = 35.dp)
                            .size(width = 450.dp, height = 50.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Orange),
                        shape = RoundedCornerShape(6.dp)

                ) {
                    Text(text = "Let's Start" , modifier = Modifier.padding(8.dp))  }

                }

            }

        }

    }
}


@Composable
fun InfoPreview(){
    val navController = rememberNavController()
    InfoScreen(navController = navController)
}


