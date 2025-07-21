package com.example.spleshscreen.NotificationSection

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spleshscreen.Authentication.LoginScreen
import com.example.spleshscreen.Navigation.Screens
import com.example.spleshscreen.ui.theme.Blue
import com.example.spleshscreen.ui.theme.LiteGray


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun Notification(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Blue
            )
    )
    {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Notification",
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
                .fillMaxHeight()
                .background(
                    color = LiteGray,
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                )
        ) {


            NotificationCard(navController)
        }


    }
}


@Composable
fun NotificationCard(navController: NavController) {


    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("Unread", "All")
    val viewModel : TodoViewModel = viewModel()

    if (viewModel.isLoading){
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 4.dp)
        }
        return
    }
    if (viewModel.errorMessage.isNotEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Error: ${viewModel.errorMessage}")
        }
        return
    }

    val allNotifications = viewModel.todoList.map { todo ->
        Notifications(notification = todo.todo , isRead =  todo.completed)
    }

    val filterNotifications = if (selectedIndex == 0) {
        allNotifications.filter { !it.isRead }

    } else {
        allNotifications
    }
    val unreadCount = allNotifications.count { !it.isRead }
    val allCount = allNotifications.count()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp)
                ),
            colors = CardDefaults.cardColors(
               containerColor =  Color.White
            ),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Row {

                options.forEachIndexed { index, label ->
                    val count = if (index == 0) unreadCount else allCount

                    Card(
                        modifier = Modifier
                            .size(width = 125.dp, height = 50.dp)
                            .clickable { selectedIndex = index }
                            .background(
                                color = if (selectedIndex == index) Blue else Color.White,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        colors = CardDefaults.cardColors(
                            containerColor = if (selectedIndex == index) Blue else Color.White
                        ),
                        elevation = CardDefaults.cardElevation(8.dp)

                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = label,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (selectedIndex == index) Color.White else Color.Black
                                )

                                if (count > 0) {
                                    Box(
                                        modifier = Modifier
                                            .sizeIn(minWidth = 20.dp)
                                            .background(
                                                color = if (selectedIndex == index) Color.White else Color.Gray,
                                                shape = CircleShape
                                            )
                                            .padding(horizontal = 6.dp, vertical = 2.dp),
                                        contentAlignment = Alignment.Center
                                    ) {

                                        Text(
                                            count.toString(),
                                            color = Color.Black
                                        )
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }


    // Notification List


    Column(modifier = Modifier.padding(12.dp)) {


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            items(filterNotifications) { item ->


                Card(
                    modifier = Modifier
                        .clickable {navController.navigate(Screens.MainScreen.Loading.route)}
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(10.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(8.dp)

                ) {



                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),

                            ) {

                            Box(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .align(alignment = Alignment.CenterVertically)
                                    .clip(CircleShape),
                                contentAlignment = Alignment.Center
                            ) {

                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Check Circle",
                                    modifier = Modifier
                                        .blur(50.dp)
                                        .size(35.dp),
                                    tint = Color.Blue
                                )

                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Check Circle",
                                    tint = Color.Blue
                                )

                        }

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(text = item.notification , color = Color.DarkGray, fontSize = 14.sp,
                            modifier = Modifier.
                            align(alignment = Alignment.CenterVertically) )


                    }}




                }
            }
        }
}




@Composable
fun NotificationPreview(){
    val navController = rememberNavController()
    Notification(navController)

}