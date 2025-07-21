//package com.example.spleshscreen.Navigation
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.compose.runtime.remember
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.composable
//import androidx.navigation.navigation
//import com.example.spleshscreen.Authentication.LoginScreen
//import com.example.spleshscreen.Authentication.RegisterScreen
//import com.example.spleshscreen.HomeScreen.BarcodeScanner
//import com.example.spleshscreen.HomeScreen.HomeScreen
//import com.example.spleshscreen.HomeScreen.ProfileDetail
//import com.example.spleshscreen.PaySlip.PaySlip
//import com.example.spleshscreen.TaskScreens.CommentScreen
//import com.example.spleshscreen.TaskScreens.Comments
//import com.example.spleshscreen.TaskScreens.MyTask
//import com.example.spleshscreen.TaskScreens.TaskDetail
//import com.example.spleshscreen.TaskScreens.TaskViewModel
//import com.example.spleshscreen.otherScreen.AttendanceScreen
//import com.example.spleshscreen.otherScreen.Events
//import com.example.spleshscreen.LeaveSection.Leave
//import com.example.spleshscreen.LeaveSection.LeaveViewModel
//import com.example.spleshscreen.NotificationSection.Loading
//import com.example.spleshscreen.NotificationSection.Notification
//import com.example.spleshscreen.UserDetailApi.AuthViewModel
//
//fun NavGraphBuilder.authGraph(navController: NavHostController) {
//    navigation(route = Screens.AuthScreen.route,
//        startDestination =  Screens.AuthScreen.LoginScreen.route) {
//
//        composable(Screens.AuthScreen.LoginScreen.route) {
//            LoginScreen(navController)
//        }
//        composable(Screens.AuthScreen.RegisterScreen.route) {
//            RegisterScreen(navController)
//        }
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//fun NavGraphBuilder.mainGraph(navController: NavHostController){
//
//    navigation(route = Screens.MainScreen.route ,
//        startDestination =  Screens.MainScreen.HomeScreen.route){
//        composable(Screens.MainScreen.HomeScreen.route) {
//            HomeScreen(navController)
//        }
//        composable(Screens.MainScreen.QrScanScreen.route) {
//            BarcodeScanner(navController)
//        }
//
//
//        composable(Screens.MainScreen.ProfileDetail.route){
//            val viewModel: AuthViewModel = viewModel()
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//
//                ProfileDetail(navController , viewModel)
//            }
//        }
//
//        composable(Screens.MainScreen.Notification.route){
//            Notification(navController)
//        }
//
//        composable(Screens.MainScreen.Loading.route){
//            Loading(navController)
//        }
//
//        composable(Screens.MainScreen.Attendance.route){
//            AttendanceScreen(navController)
//        }
//        composable(Screens.MainScreen.PaySlip.route){
//            PaySlip(navController)
//        }
//        composable(Screens.MainScreen.Leaves.route){
//            val viewModel: LeaveViewModel = viewModel()
//            Leave(navController , viewModel)
//        }
//        composable(Screens.MainScreen.Events.route){
//            Events(navController)
//        }
//    }
//}
//
//fun NavGraphBuilder.taskGraph(navController: NavHostController) {
//    navigation(
//        startDestination = Screens.Task.MyTask.route,
//        route = Screens.Task.route
//    ) {
//
//        composable(Screens.Task.MyTask.route) { backStackEntry ->
//
//            val parentEntry = remember(backStackEntry) {
//                navController.getBackStackEntry(Screens.Task.route)
//            }
//            val viewModel: TaskViewModel = viewModel(parentEntry)
//            MyTask(navController, viewModel)
//        }
//
//        composable(Screens.Task.TaskDetail.route) { backStackEntry ->
//            val parentEntry = remember(backStackEntry) {
//                navController.getBackStackEntry(Screens.Task.route)
//            }
//            val viewModel: TaskViewModel = viewModel(parentEntry)
//            TaskDetail(navController, viewModel)
//        }
//    }
//}
//
//
//fun NavGraphBuilder.commentGraph(navController: NavHostController){
//
//    navigation(route = Screens.Comment.route, startDestination = Screens.Comment.CommentScreen.route){
//        composable(Screens.Comment.CommentScreen.route){
//            CommentScreen(navController)
//        }
//        composable(Screens.Comment.Comments.route){
//            Comments(navController)
//        }
//    }
//}