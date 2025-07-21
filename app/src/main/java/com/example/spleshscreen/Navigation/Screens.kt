package com.example.spleshscreen.Navigation

open class Screens(val route: String) {

    object SplashScreen : Screens("splash_screen")
    object InfoScreen : Screens("intro_screen")
    object AuthScreen : Screens("auth_screen") {

        object LoginScreen : Screens("login_screen")
        object RegisterScreen : Screens("register_screen")

    }
object MainScreen : Screens("main_screen"){

    object HomeScreen :  Screens("home_screen")
    object  QrScanScreen : Screens("qr_screen")
    object ProfileDetail : Screens("profile_detail")
    object Notification : Screens("notification")
    object Loading : Screens("loading")
    object Attendance : Screens("attendance")
    object PaySlip : Screens("pay_slip")
    object Leaves : Screens("leaves")
    object Events : Screens("events")

}

    object Task :  Screens("task"){

        object MyTask : Screens("my_task")
        object TaskDetail: Screens("task_detail")



    }

    object Comment : Screens("comment"){
        object CommentScreen : Screens("comment_screen")
        object Comments : Screens("comments")

    }


    object MonthPay :  Screens("month_pay")
    object LeaveRequest : Screens("leave_request")




}