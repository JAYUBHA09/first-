package com.example.spleshscreen.Authentication


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.spleshscreen.R
import com.example.spleshscreen.Navigation.Screens
import com.example.spleshscreen.UserDetailApi.AuthViewModel
import com.example.spleshscreen.UserDetailApi.UserPreferences
import com.example.spleshscreen.ui.theme.Blue
import com.example.spleshscreen.ui.theme.Orange


@Composable
fun LoginScreen(navController: NavController) {

    val viewModel: AuthViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    val loginSuccess by remember { derivedStateOf { viewModel.loginSuccess } }

    val context = LocalContext.current
    val prefs = remember { UserPreferences(context) }



    LaunchedEffect(loginSuccess) {
        if (loginSuccess) {
            navController.navigate(Screens.MainScreen.HomeScreen.route) {
                popUpTo(Screens.AuthScreen.LoginScreen.route) {
                    inclusive = true
                }
            }
            viewModel.loginSuccess
        }
    }

    Column(
        modifier = Modifier.padding(top = 16.dp)

    ) {

        Image(
            painter = painterResource(id = R.drawable.loginscreen),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {

                    Text(
                        text = "Login",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        modifier = Modifier.padding(16.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))


                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = {
                            Text(
                                text = "Enter your Email",
                                style = TextStyle(
                                    fontSize = 14.sp
                                ),
                                color = Color.LightGray
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
                        }, modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(width = 350.dp, height = 50.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                    )

                    Spacer(modifier = Modifier.height(20.dp))



                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = {
                            Text(
                                text = "Enter your Password",
                                style = TextStyle(fontSize = 14.sp),
                                color = Color.LightGray
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Lock,
                                contentDescription = "Password icon"
                            )
                        },
                        trailingIcon = {
                            val visibilityIcon = if (isPasswordVisible)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff

                            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                                Icon(
                                    imageVector = visibilityIcon,
                                    contentDescription = "Toggle password visibility"
                                )
                            }
                        },
                        visualTransformation = if (isPasswordVisible)
                            VisualTransformation.None
                        else
                            PasswordVisualTransformation(),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(width = 350.dp, height = 50.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextButton(onClick = {} ,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .align(Alignment.End)) {
                        Text(
                            text = "Forgot Password?",
                            style = TextStyle(color = Color(0xFFF0B66A)),
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { viewModel.login(email, password , prefs) },
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally)
                            .size(width = 350.dp, height = 45.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Orange
                        ),
                        shape = RoundedCornerShape(8.dp)

                    )
                    {
                        if (viewModel.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White)
                        } else {
                            Text("Login")
                        }
                    }
                }


                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        navController.navigate(route = Screens.AuthScreen.RegisterScreen.route) {
                            launchSingleTop = true
                        }
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                        .size(width = 350.dp, height = 45.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Orange
                    ),
                    shape = RoundedCornerShape(8.dp)

                ) {
                    Text(text = "Register")
                }
                if (viewModel.errorMessage.isNotBlank()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = viewModel.errorMessage,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }


            }
        }


    }

}
