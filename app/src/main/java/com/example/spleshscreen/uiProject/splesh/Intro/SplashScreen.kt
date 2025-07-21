package com.example.spleshscreen.uiProject.splesh.Intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.spleshscreen.R
import com.example.spleshscreen.Navigation.Screens
import com.example.spleshscreen.UserDetailApi.UserPreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull


@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }

    LaunchedEffect(true) {
        delay(1000)

        val isFirstLaunch = userPrefs.isFirstLaunchFlow.first()
        val isLoggedIn = userPrefs.accessTokenFlow.firstOrNull()?.isNotEmpty() == true

        val destination = when {
            isFirstLaunch -> Screens.InfoScreen.route
            isLoggedIn -> Screens.MainScreen.HomeScreen.route
            else -> Screens.AuthScreen.LoginScreen.route
        }

        navController.navigate(destination) {
            popUpTo(Screens.SplashScreen.route) { inclusive = true }
            launchSingleTop = true
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.canvas),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}