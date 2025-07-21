package com.example.spleshscreen.Authentication

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
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spleshscreen.R
import com.example.spleshscreen.Navigation.Screens
import com.example.spleshscreen.UserDetailApi.AuthViewModel
import com.example.spleshscreen.ui.theme.Blue
import com.example.spleshscreen.ui.theme.Orange


@Composable
fun RegisterScreen(navController: NavController) {

    val viewModel: AuthViewModel = viewModel()
    var email by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}



    Column(modifier = Modifier.padding(top = 16.dp)) {

        Image(
            painterResource(R.drawable.resister),
            contentDescription = "Register Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(top = 15.dp)

        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Blue,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Blue,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
            ) {

                Text(
                    text = "Register",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .padding(top = 30.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))


                OutlinedTextField(
                    value = "",
                    onValueChange = {  },
                    placeholder = {
                        Text(
                            text = "Enter First Name",
                            style = TextStyle(
                                fontSize = 14.sp
                            ),
                            color = Color.DarkGray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Person,
                            contentDescription = "person icon"
                        )
                    }, modifier = Modifier
                        .padding(start = 16.dp , end = 16.dp)
                        .align(Alignment.CenterHorizontally)
                        .size(width = 370.dp, height = 51.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                )

                Spacer(modifier = Modifier.height(20.dp))


                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    placeholder = {
                        Text(
                            text = "Enter Last Name",
                            style = TextStyle(
                                fontSize = 14.sp
                            ),
                            color = Color.DarkGray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Person,
                            contentDescription = "person Icon"
                        )
                    },
                    modifier = Modifier
                        .padding(start = 16.dp , end = 16.dp)
                        .align(Alignment.CenterHorizontally)
                        .size(width = 370.dp, height = 51.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                )
                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = {email = it },
                    placeholder = {
                        Text(
                            text = "Enter your Email",
                            style = TextStyle(
                                fontSize = 14.sp
                            ),
                            color = Color.DarkGray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Email,
                            contentDescription = "Email Icon"
                        )
                    },
                    modifier = Modifier
                        .padding(start = 16.dp , end = 16.dp)
                        .align(Alignment.CenterHorizontally)
                        .size(width = 370.dp, height = 51.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                )
                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = {
                        Text(
                            text = "Enter your Password",
                            style = TextStyle(
                                fontSize = 14.sp
                            ),
                            color = Color.DarkGray
                        )
                    }, colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Lock,
                            contentDescription = "Localized description"
                        )
                    },
                    modifier = Modifier
                        .padding(start = 16.dp , end = 16.dp)
                        .align(Alignment.CenterHorizontally)
                        .size(width = 370.dp, height = 51.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 30.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {

                    Button(onClick = {},
                        modifier = Modifier
                            .padding(start = 16.dp , end = 16.dp)
                            .align(Alignment.CenterHorizontally)
                            .size(width = 370.dp, height = 51.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Orange
                        ),
                        shape = RoundedCornerShape(14.dp)

                    ) {
                        Text("Register")
                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {

                        Text(
                            text = "Already have an account?",
                            style = TextStyle(fontSize = 12.sp, color = Color.White)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Login here",
                            style = TextStyle(fontSize = 12.sp, color = Orange),
                            modifier = Modifier.clickable(onClick = {
                                navController.navigate(Screens.AuthScreen.LoginScreen.route) {
                                    popUpTo(
                                        0
                                    )
                                }
                            })
                        )
                    }

                }

            }
        }
    }
}


@Composable
fun resisterScreenPreview() {
    val navController = rememberNavController()

    RegisterScreen(navController)

}