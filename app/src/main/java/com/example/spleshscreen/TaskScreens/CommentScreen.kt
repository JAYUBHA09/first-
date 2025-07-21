package com.example.spleshscreen.TaskScreens

import android.R.attr.horizontalDivider
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.spleshscreen.Navigation.Screens
import com.example.spleshscreen.R
import com.example.spleshscreen.ui.theme.Blue
import com.example.spleshscreen.ui.theme.LiteGray
import com.example.spleshscreen.ui.theme.NavyBlue
import com.example.spleshscreen.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentScreen(navController: NavController){

val comment = listOf<Comments>(
    Comments(R.drawable.account , "Smith" , "These are the project dependencies, including PHP version and Monolog" , "14/02/20023"),
    Comments(R.drawable.account , "Smith" , "These are the project dependencies, including PHP version and Monolog" , "14/02/20023"),
    Comments(R.drawable.account , "Smith" , "These are the project dependencies, including PHP version and Monolog" , "14/02/20023"),
    Comments(R.drawable.account , "Smith" , "These are the project dependencies, including PHP version and Monolog" , "14/02/20023")



)



    Column(modifier = Modifier.background(Blue)) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Comments",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = {navController.popBackStack()}) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            actions = {
                IconButton(onClick = {navController.navigate(Screens.Comment.Comments.route)}) {

                    Image(painter = painterResource(R.drawable.edit_square),
                        contentDescription = null ,
                        modifier = Modifier.size(28.dp)
                            .background(color = Color.White ,
                                shape = RoundedCornerShape(6.dp)))

                }
            },

            colors = TopAppBarDefaults.topAppBarColors(containerColor = Blue)
        )


        Column(

            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = LiteGray,
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                )
        ){

            LazyColumn(modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()) {
                items(comment) { item ->
                    Card(
                        modifier = Modifier.padding(16.dp),
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.cardElevation(10.dp)
                    ) {

                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Image(
                                painter = painterResource(item.img),
                                contentDescription = null,
                                modifier = Modifier.size(50.dp)
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            Column {
                                Text(
                                    text = item.name,
                                    color = NavyBlue,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(text = item.comment, color = NavyBlue, fontSize = 15.sp)

                                Spacer(modifier = Modifier.height(6.dp))

                                Row(verticalAlignment = Alignment.CenterVertically) {

                                    Icon(
                                        imageVector = Icons.Default.DateRange,
                                        contentDescription = null, tint = Color.Gray,
                                        modifier = Modifier.size(15.dp)
                                    )

                                    Spacer(modifier = Modifier.width(4.dp))

                                    Text(text = item.date, color = Color.Gray, fontSize = 12.sp)
                                }

                            }
                        }
                    }

                }
            }


        }

    }

}

data class Comments(val img: Int , val name : String , val comment: String , val date : String)


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun Comments(navController: NavController){

    val ShowDialog = remember { mutableStateOf(false) }

    CommentDialog(ShowDialog = ShowDialog)

    val comment = listOf<Comments>(
        Comments(R.drawable.account , "Smith" , "These are the project dependencies, including PHP version and Monolog" , "14 Feb 20023"),
        Comments(R.drawable.account , "Rozy" , "These are the project dependencies, including PHP version and Monolog" , "14 Feb 20023"),
        Comments(R.drawable.account , "Harry" , "These are the project dependencies, including PHP version and Monolog" , "14 Feb 20023"),
        Comments(R.drawable.account , "Smith" , "These are the project dependencies, including PHP version and Monolog" , "14 Feb 20023"),
        Comments(R.drawable.account , "Harry" , "These are the project dependencies, including PHP version and Monolog" , "14 Feb 20023"),
        Comments(R.drawable.account , "Smith" , "These are the project dependencies, including PHP version and Monolog" , "14 Feb 20023"),
        Comments(R.drawable.account , "Rozy" , "These are the project dependencies, including PHP version and Monolog" , "14 Feb 20023"),
        Comments(R.drawable.account , "Smharryith" , "These are the project dependencies, including PHP version and Monolog" , "14 Feb 20023")

    )



    Column(modifier = Modifier.background(Blue)) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Comments",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = {navController.popBackStack()}) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },

            colors = TopAppBarDefaults.topAppBarColors(containerColor = Blue)
        )
        Column(modifier = Modifier
            .fillMaxSize()
            .background(
                color = LiteGray,
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
            )) {

                Box(modifier =Modifier.weight(1f)
                ) {

                    LazyColumn(
                        modifier = Modifier
                            .background(
                                color = LiteGray,
                                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                            )
                    ) {
                        items(comment) { item ->


                            Row(
                                modifier = Modifier
                                    .padding(start = 10.dp , end = 10.dp)
                                    .padding(16.dp)
                                    .background(color = LiteGray),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Image(
                                    painter = painterResource(item.img),
                                    contentDescription = null,
                                    modifier = Modifier.size(50.dp)
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                Column {
                                    Text(text = item.name, color = NavyBlue, fontWeight = FontWeight.Bold)

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Text(text = item.comment, color = NavyBlue, fontSize = 15.sp)

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Row(verticalAlignment = Alignment.CenterVertically) {

                                        Icon(
                                            imageVector = Icons.Default.DateRange,
                                            contentDescription = null, tint = Color.Gray,
                                            modifier = Modifier.size(15.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))

                                        Text(text = item.date , color = Color.Gray , fontSize = 12.sp)
                                    }

                                }

                            }
                            HorizontalDivider(
                                thickness = 2.dp,
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth()
                            )


                        }

                    }
                }
                Box(
                    modifier = Modifier
                        .clickable(onClick = {ShowDialog.value = true})
                        .fillMaxWidth()
                        .background(Blue)
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center

                ) {
                    Text("Write Comments", color = Color.White, fontWeight = FontWeight.Bold)
                }


            }

        }


        }






