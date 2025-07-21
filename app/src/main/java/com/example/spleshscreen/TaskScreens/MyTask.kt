package com.example.spleshscreen.TaskScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spleshscreen.Navigation.Screens
import com.example.spleshscreen.R
import com.example.spleshscreen.TaskScreens.TaskCard
import com.example.spleshscreen.ui.theme.Blue
import com.example.spleshscreen.ui.theme.DarkGreen
import com.example.spleshscreen.ui.theme.DarkRed
import com.example.spleshscreen.ui.theme.DarkYellow
import com.example.spleshscreen.ui.theme.LiteGray
import com.example.spleshscreen.ui.theme.LiteGreen
import com.example.spleshscreen.ui.theme.LiteRed
import com.example.spleshscreen.ui.theme.LiteYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTask(navController: NavController ,viewModel: TaskViewModel) {

    Column( modifier = Modifier.background(Blue)) {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "My Task",
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
                .background(
                    color = LiteGray,
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                )
        ) {
//            FilterTab()



            TaskCard(navController , viewModel)





        }
    }

}

data class FilterTab(val tab: String, var pendingTaskNo: Int)



@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onFilterSelected: (String) -> Unit
) {
    var filterMenuExpanded by remember { mutableStateOf(false) }
    val filters = listOf("All Task", "In Progress", "Completed")


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp , end = 16.dp, top = 10.dp , bottom = 10.dp),
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

        Column {
            Image(
                painter = painterResource(R.drawable.filter_list),
                contentDescription = "Filter",
                modifier = Modifier
                    .clickable(onClick = { filterMenuExpanded = true })
                    .size(height = 45.dp, width = 45.dp)
                    .background(color = Blue, shape = RoundedCornerShape(6.dp))
            )

            DropdownMenu(
                expanded = filterMenuExpanded,
                onDismissRequest = { filterMenuExpanded = false }
            ) {
                filters.forEach { filter ->
                    DropdownMenuItem(
                        text = { Text(text = filter) },
                        onClick = {
                            onFilterSelected(filter)
                            filterMenuExpanded = false
                        }
                    )

                }
            }
        }

    }
}


//@Composable
//fun SearchScreen() {
//    var searchQuery by remember { mutableStateOf("") }
//
//    Column() {
//        SearchBar(
//            query = searchQuery,
//            onQueryChanged = { searchQuery = it } ,
//
//        )
//    }
//}


@Composable
fun TaskCard(navController: NavController , viewModel: TaskViewModel) {

    var searchQuery by remember { mutableStateOf("") }
    var selectedFilterIndex by remember { mutableIntStateOf(0) }
    val filters = listOf("All Task", "In Progress", "Completed")

    val task = listOf(
        Task(
            0,
            "Online food order web design", "High", "Completed", "14 Nov 2023", R.drawable.pause
        ),
        Task(
            1,
            "Changes in Event App", "High", "Completed", "10 Nov 2023" ,R.drawable.play
        ),
        Task(
            2,
            "API changes Test user", "High", "In Progress", "06 Nov 2023",R.drawable.pause
        )
    )

    val filteredTasks = when (filters[selectedFilterIndex]) {
        "All Task" -> task
        else -> task.filter { it.status == filters[selectedFilterIndex] }
    }
    val searchResults = filteredTasks.filter {it.task.contains(searchQuery, ignoreCase = true)}

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(12.dp)) {


        SearchBar(
            query = searchQuery,
            onQueryChanged = { searchQuery = it } ,
            onFilterSelected = { selected ->
                selectedFilterIndex = filters.indexOf(selected)
            }
        )




        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(searchResults) { task ->


                Card(
                    modifier = Modifier
                        .clickable(onClick = {
                            viewModel.selectedTask(task)
                            navController.navigate(Screens.Task.TaskDetail.route)
                        })
                        .fillMaxSize()
                        .padding(start = 16.dp , end = 16.dp, top = 10.dp , bottom = 10.dp)                        .background(
                            color = Color.White,
                            RoundedCornerShape(10.dp),

                            ),
                    colors = CardDefaults.cardColors(Color.White),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(modifier = Modifier.padding(6.dp)) {

                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        )
                        {
                            Text(
                                text = task.task,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp, modifier = Modifier.padding(8.dp)
                            )

                            //        Spacer(modifier = Modifier.width(50.dp))

                            Row(
                                modifier = Modifier.padding(6.dp),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.visibility),
                                    contentDescription = "Play",
                                    modifier = Modifier.size(24.dp),
                                    tint = Blue
                                )
                                Spacer(modifier = Modifier.width(8.dp))

                                Icon(
                                    painter = painterResource(task.icon),
                                    contentDescription = "task",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(
                                            color = if (task.icon == R.drawable.pause) DarkGreen else DarkRed,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                )

                            }


                        }

                        Row {
                            Box(
                                modifier = Modifier
                                    .padding(6.dp)
                                    .background(
                                        color = when (task.priority) {
                                            "High" -> LiteRed
                                            "Medium" -> LiteYellow
                                            else -> {
                                                LiteGreen
                                            }
                                        },
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                , contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = task.priority,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(top = 2.dp , bottom = 2.dp , start = 8.dp , end = 8.dp),
                                    color = when (task.priority) {
                                        "High" -> DarkRed
                                        "Medium" -> DarkYellow
                                        else -> {
                                            DarkGreen
                                        }
                                    }
                                )
                            }
                            Spacer(modifier = Modifier.width(2.dp))

                            Box(
                                modifier = Modifier
                                    .padding(6.dp)
                                    .background(
                                        color = when (task.status) {
                                            "Completed" -> LiteGreen
                                            "In Progress" -> LiteYellow
                                            else -> {
                                                LiteRed
                                            }
                                        },
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                , contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = task.status,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(top = 2.dp , bottom = 2.dp , start = 8.dp , end = 8.dp),
                                    color = when (task.status) {
                                        "Completed" -> DarkGreen
                                        "In Progress" -> DarkYellow
                                        else -> {
                                            DarkRed
                                        }
                                    }
                                )

                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 6.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(imageVector = Icons.Default.DateRange, contentDescription = "Date" , tint = Color.DarkGray , modifier = Modifier.size(20.dp))

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(text = task.date, color = Color.DarkGray, fontWeight = FontWeight.Medium , fontSize = 12.sp)

                        }


                    }

                }
            }


        }
    }
}

@Composable
fun TaskPreview(){
    val viewModel: TaskViewModel = viewModel()
    val navController = rememberNavController()

    MyTask(navController , viewModel)
}