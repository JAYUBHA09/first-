package com.example.spleshscreen.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.spleshscreen.AttandenceSection.Attendance
import com.example.spleshscreen.Authentication.LoginScreen
import com.example.spleshscreen.Authentication.RegisterScreen
import com.example.spleshscreen.HomeScreen.BarcodeScanner
import com.example.spleshscreen.HomeScreen.HomeScreen
import com.example.spleshscreen.HomeScreen.ProfileDetail
import com.example.spleshscreen.LeaveSection.Leave
import com.example.spleshscreen.PaySlip.Months
import com.example.spleshscreen.LeaveSection.LeaveRequest
import com.example.spleshscreen.LeaveSection.LeaveViewModel
import com.example.spleshscreen.NotificationSection.Notification
import com.example.spleshscreen.PaySlip.PaySlip
import com.example.spleshscreen.TaskScreens.CommentScreen
import com.example.spleshscreen.TaskScreens.Comments
import com.example.spleshscreen.TaskScreens.MyTask
import com.example.spleshscreen.TaskScreens.TaskDetail
import com.example.spleshscreen.TaskScreens.TaskViewModel
import com.example.spleshscreen.UserDetailApi.AuthViewModel
import com.example.spleshscreen.otherScreen.Events
import com.example.spleshscreen.uiProject.splesh.Intro.InfoScreen
import com.example.spleshscreen.uiProject.splesh.Intro.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavcontroler(navController: NavHostController){

   NavHost(navController = navController , startDestination = Screens.SplashScreen.route) {

        composable(Screens.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(Screens.InfoScreen.route){
            InfoScreen(navController)
        }
        composable(Screens.MonthPay.route){
            Months(navController)

        }
        composable(Screens.LeaveRequest.route){
            LeaveRequest(navController)
        }



        navigation(route = Screens.AuthScreen.route , startDestination = Screens.AuthScreen.LoginScreen.route){
            composable(Screens.AuthScreen.LoginScreen.route){
                LoginScreen(navController)
            }

            composable(Screens.AuthScreen.RegisterScreen.route){
                RegisterScreen(navController)
            }



        }

        navigation(route = Screens.MainScreen.route ,
            startDestination = Screens.MainScreen.HomeScreen.route){


            composable(Screens.MainScreen.HomeScreen.route) {
                val viewModel: AuthViewModel = viewModel()
                HomeScreen(navController , viewModel)
            }

            composable(Screens.MainScreen.QrScanScreen.route) {
                BarcodeScanner(navController)
            }

            composable(Screens.MainScreen.ProfileDetail.route) {
                val viewModel: AuthViewModel = viewModel()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    ProfileDetail(navController, viewModel)
                }
            }

            composable(Screens.MainScreen.Notification.route){
                Notification(navController)
            }

            composable(Screens.MainScreen.Attendance.route){
                Attendance(navController)
            }
            composable(Screens.MainScreen.PaySlip.route){
                PaySlip(navController)
            }
            composable(Screens.MainScreen.Leaves.route){
                val viewModel: LeaveViewModel = viewModel()
                Leave(
                    navController,
                    viewModel = viewModel
                )
            }
            composable(Screens.MainScreen.Events.route){
                Events(navController)
            }

        }

        navigation(route = Screens.Task.route, startDestination = Screens.Task.MyTask.route) {

            composable(Screens.Task.MyTask.route) { backStackEntry ->

                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Screens.Task.route)
                }
                val viewModel: TaskViewModel = viewModel(parentEntry)
                MyTask(navController, viewModel)
            }

            composable(Screens.Task.TaskDetail.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Screens.Task.route)
                }
                val viewModel: TaskViewModel = viewModel(parentEntry)
                TaskDetail(navController, viewModel)
            }

        }

        navigation(route = Screens.Comment.route , startDestination = Screens.Comment.CommentScreen.route){
            composable(Screens.Comment.CommentScreen.route){
                CommentScreen(navController)
            }
            composable(Screens.Comment.Comments.route){
                Comments(navController)
            }
        }

    }

}