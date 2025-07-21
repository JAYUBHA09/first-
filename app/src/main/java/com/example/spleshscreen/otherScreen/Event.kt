package com.example.spleshscreen.otherScreen

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.spleshscreen.R
import com.example.spleshscreen.ui.theme.Blue
import com.example.spleshscreen.ui.theme.LiteGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Events(navController: NavController){
    var searchQuery by remember { mutableStateOf("") }


    val ScrollState = rememberScrollState()


    Column( modifier = Modifier.fillMaxSize()
        .background(color = Blue))
    {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Events and Holidays",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
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
                .verticalScroll(ScrollState)
                .background(
                    LiteGray,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .padding(top = 16.dp)
        ) {
            Column(modifier = Modifier.padding(start = 20.dp , end = 20.dp)) {
                EventTogel()

            }
            Box(modifier = Modifier.padding(6.dp)) {
                EventSearch(
                    query = searchQuery,
                    onQueryChanged = {searchQuery = it}
                )
            }
        }


        }
}


@Composable

fun EventTogel(){

    var selectedIndex by remember { mutableIntStateOf(0) }

    val options = listOf("Event", "Holiday")


    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 15.dp),
        horizontalArrangement = Arrangement.Center) {

        options.forEachIndexed { index, label ->
            Box(
                modifier = Modifier
                    .size(width = 175.dp, height = 40.dp)
                    .clickable(onClick = { selectedIndex = index })
                    .background(
                        color = if (selectedIndex == index) Blue else Color.White,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = label ,
                    fontWeight = FontWeight.Bold,
                    color = if (selectedIndex == index)Color.White else Color.DarkGray
                )

            }
        }
    }

}

@Composable
fun EventSearch(
    query: String,
    onQueryChanged: (String) -> Unit){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChanged,
            placeholder = { Text("Search...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))

        Image(
            painter = painterResource(R.drawable.filter_list),
            contentDescription = "Filter",
            modifier = Modifier
                .clickable(onClick = { })
                .size(height = 45.dp, width = 45.dp)
                .background(color = Blue, shape = RoundedCornerShape(6.dp))
        )

    }
}