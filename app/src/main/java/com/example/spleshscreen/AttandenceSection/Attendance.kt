package com.example.spleshscreen.AttandenceSection

import Calendar
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spleshscreen.R
import com.example.spleshscreen.ui.theme.Blue
import com.example.spleshscreen.ui.theme.LiteGray
import com.example.spleshscreen.ui.theme.NavyBlue
import com.example.spleshscreen.ui.theme.Orange
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale


@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceScreen(navController: NavController) {

    val scrollState = rememberScrollState()


        Column( modifier = Modifier.fillMaxSize()
            .background(color = Blue))
        {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Attendance Detail",
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
                    .background(
                        LiteGray,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .verticalScroll(scrollState)
                    .padding(top = 16.dp)
            ) {
                AttendanceSummaryCard()

            }


        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AttendanceSummaryCard() {

    data class Attendance(val days: Int, val status: String, val color: Color)



    val items = listOf(
        Attendance(16, "Present", Color(0xFF112772)),
        Attendance(4, "Half Day", Color(0xFFEF862E)),
        Attendance(2, "Absent", Color(0xff62E3CC)),
        Attendance(22, "Total", Color(0xFFF3A8A2))
    )

  Column( modifier = Modifier
      .background(LiteGray)) {


      Card(
          modifier = Modifier
              .fillMaxWidth()
              .padding(start = 30.dp , end = 30.dp , top = 10.dp, bottom = 10.dp)
              .height(88.dp)
              .background(
                  color = Color.White,
                  shape = RoundedCornerShape(10.dp)
              ),
          elevation = CardDefaults.cardElevation(8.dp)
      ) {

          Row(
              modifier = Modifier
                  .fillMaxWidth()
                  .fillMaxSize()
                  .background(
                      Color.White,
                      RoundedCornerShape(8.dp)
                  ),
              horizontalArrangement = Arrangement.SpaceEvenly,
              verticalAlignment = Alignment.CenterVertically
          ) {


              items.forEach { item ->

                  Column(
                      modifier = Modifier.padding(4.dp),
                      verticalArrangement = Arrangement.SpaceAround,
                      horizontalAlignment = Alignment.CenterHorizontally
                  )  {
                      Text(
                          text = item.days.toString(),
                          textAlign = TextAlign.Center,
                          style = TextStyle(
                              fontSize = 23.sp,
                              fontWeight = FontWeight.Bold,
                              color = item.color
                          )
                      )

                      Spacer(modifier = Modifier.height(6.dp))


                      Text(
                          text = item.status, style = TextStyle(
                              fontSize = 14.sp,
                              fontWeight = FontWeight.Medium,
                              color = Color.Gray
                          )

                      )


                  }
              }
          }
      }

      val context = LocalContext.current
      var selectedDate by remember { mutableStateOf(LocalDate.now()) }

      val DatePicker = remember {
          android.app.DatePickerDialog(
              context,
              { _, year, month, dayOfMonth ->
                  selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
              },
              selectedDate.year,
              selectedDate.monthValue - 1,
              selectedDate.dayOfMonth
          ).apply{
              datePicker.maxDate = System.currentTimeMillis()

          }
      }


      Row(
          modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
          verticalAlignment = Alignment.Top,
          horizontalArrangement = Arrangement.SpaceBetween
      ) {
          Text(
              text = selectedDate.toString(), style = TextStyle(
                  fontSize = 15.sp,
                  fontWeight = FontWeight.ExtraBold,
                  color = NavyBlue
              ), modifier = Modifier
                  .padding(start = 33.dp , end = 35.dp)
                  .align(Alignment.CenterVertically)
          )
          Spacer(modifier = Modifier.width(50.dp))

          Icon(Icons.Default.DateRange ,
              contentDescription = "Datepicker" ,
              tint = Orange,
              modifier = Modifier
                  .padding(start = 30.dp , end = 30.dp)
                  .clickable{DatePicker.show()}
                  .align(Alignment.Bottom))


      }
 // Calender
     Calendar(
         selectedDate ,
         onDateSelected = {selectedDate = it}
     )

      // Check In Out Card List

      CheckInOut(selectedDate)
  }

    }





@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckInOut(selectedDate: LocalDate) {

    data class Check(val img: Int, val checkIn: String, val checkOut: String , val date: LocalDate)

    val persons = listOf(
        Check(R.drawable.account, "09:30 AM", "10:50 AM" ,LocalDate.of(2025, 7, 9)),
        Check(R.drawable.account, "09:30 AM", "10:50 AM" ,LocalDate.of(2025, 7, 9)),
        Check(R.drawable.account, "11:30 AM", "12:50 PM" ,LocalDate.of(2025, 7, 3)),
        Check(R.drawable.account, "10:30 AM", "01:50 PM" ,LocalDate.of(2025, 6, 30)),
        Check(R.drawable.account, "11:15 AM", "01:50 PM" ,LocalDate.of(2025, 6, 29)),
        Check(R.drawable.account, "09:45 AM", "02:50 PM" ,LocalDate.of(2025, 6, 28)),
        Check(R.drawable.account, "11:45 AM", "03:50 PM" ,LocalDate.of(2025, 6, 27)),
        Check(R.drawable.account, "01:45 AM", "06:50 PM" ,LocalDate.of(2025, 6, 26)),
        Check(R.drawable.account, "02:45 AM", "05:50 PM" ,LocalDate.of(2025, 6, 25)),
    )

    val filteredPerson = persons.filter{it.date == selectedDate}

    val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.US)

    val totalMinutes = filteredPerson.sumOf {
        item ->
        try {
           val inTime = LocalTime.parse(item.checkIn, timeFormatter)
           val outTime = LocalTime.parse(item.checkOut, timeFormatter)
            val duration = Duration.between(inTime , outTime).toMinutes()
            duration.coerceAtLeast(0)
        } catch (e: Exception){
            0L
        }
    }
    val totalHours = totalMinutes / 60
    val remainderMinutes = totalMinutes % 60
    val totalFormatted = "%02d:%02d".format(totalHours, remainderMinutes)




    Column(
        modifier = Modifier
            .padding(start = 30.dp , end = 30.dp , top = 10.dp, bottom = 10.dp)
            .fillMaxSize()
            .background(LiteGray)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            Text(
                text = "Total Hours", style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = NavyBlue
                ), modifier = Modifier

                    .align(Alignment.Bottom)
            )
            Spacer(modifier = Modifier.width(50.dp))

            Text(
                text = totalFormatted, style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Blue
                ), modifier = Modifier
                    .align(Alignment.Bottom)
            )

        }
        Box(modifier = Modifier.height(500.dp)) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier
                    .fillMaxHeight()) {
                items(filteredPerson) { item ->

                    val durationMinutes = try {
                        val inTime = LocalTime.parse(item.checkIn , timeFormatter)
                        val outTime = LocalTime.parse(item.checkOut , timeFormatter)
                        Duration.between(inTime,outTime).toMinutes().coerceAtLeast(0)
                    }catch (e: Exception){
                        0L
                    }
                    val durationText = "${durationMinutes / 60}h ${durationMinutes % 60}m"

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp , bottom = 12.dp)
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
                                .fillMaxWidth()
                                .padding(top = 16.dp , bottom = 16.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Image(
                                painter = painterResource(id = item.img),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(50.dp)
                                    .align(Alignment.CenterVertically)
                                    .clip(CircleShape)
                            )



                            Column(modifier = Modifier.padding(2.dp),
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("Check-In", fontSize = 12.sp, color = NavyBlue , fontWeight = FontWeight.Medium)
                                Text(item.checkIn, fontSize = 10.sp, color = Color(0xFF16B144) , fontWeight = FontWeight.Medium)
                            }



                            Column(modifier = Modifier.padding(2.dp),
                                horizontalAlignment = Alignment.CenterHorizontally) {

                                Text("Check-Out", fontSize = 12.sp, color = NavyBlue, fontWeight = FontWeight.Medium)
                                Text(item.checkOut, fontSize = 10.sp,  color = Color(0xFFFF0000) , fontWeight = FontWeight.Medium)
                            }

                            Column(modifier = Modifier.padding(2.dp),
                                horizontalAlignment = Alignment.CenterHorizontally)
                            {
                                Text("Duration", fontSize = 12.sp, color = NavyBlue, fontWeight = FontWeight.Bold)
                                Text(durationText, fontSize = 10.sp, color = Blue , fontWeight = FontWeight.Medium)
                            }
                        }
                    }

                }

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AttendancePreview(){

    val navController = rememberNavController()
    AttendanceScreen(navController)

}