import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spleshscreen.HomeScreen.LeaveAlert
import com.example.spleshscreen.HomeScreen.WeeklyGraph
import com.example.spleshscreen.Navigation.Screens
import com.example.spleshscreen.R
import com.example.spleshscreen.ui.theme.LiteGray


data class GridItem(
    val title: String,
    val iconRes: Int,
    val backgroundColor: Color ,
    val onClick: () -> Unit
)
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun HomeGrid(item: List<GridItem>) {


   LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .background(LiteGray),
       // contentPadding = PaddingValues(6.dp),
        horizontalArrangement = Arrangement.Center
    ) {

       items(item) { item ->

                Card(
                    modifier = Modifier
                        .size(width = 172.dp , height = 137.dp)
                        .clickable(onClick = {item.onClick()})
                        .padding(10.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    colors = CardDefaults.cardColors(item.backgroundColor)


                ) {

                    Column(
                        modifier = Modifier.fillMaxSize().padding(start = 15.dp , top = 15.dp),

                    ) {
                        Image(
                            painter = painterResource(id = item.iconRes),
                            contentDescription = item.title,
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                                .clip(RoundedCornerShape(18.dp)))


                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = item.title,
                            fontWeight = FontWeight.Medium,
                            fontSize = 15.sp,
                            color = Color.White
                        )

                    }


            }
        }
    }

}


@Composable
fun MainGrid(navController: NavController){



    val items = listOf(

        GridItem("Attendance" , R.drawable.attendance , Color(0xFFF3A8A2) , {navController.navigate(Screens.MainScreen.Attendance.route)}),
        GridItem("Notification" , R.drawable.notification, Color(0xFF63D5E5), {navController.navigate(Screens.MainScreen.Notification.route)}),
        GridItem("Payslip" , R.drawable.payslip , Color(0xFF809CEE) , {navController.navigate(Screens.MainScreen.PaySlip.route)}),
        GridItem("Leaves" , R.drawable.leave , Color(0xFFE9B16F) , {navController.navigate(Screens.MainScreen.Leaves.route)}),
        GridItem("Events" , R.drawable.event , Color(0xFF62E3B7) , {navController.navigate(Screens.MainScreen.Events.route)}),
        GridItem("My Task" , R.drawable.task , Color(0xFFF5A6BE) , {navController.navigate(Screens.Task.MyTask.route)})
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(410.dp)
    ) {
        HomeGrid(item = items)
    }


}


@Composable
fun HomeGridPreview(){

    val navController = rememberNavController()
    MainGrid(navController)
}