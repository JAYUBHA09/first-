import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spleshscreen.ui.theme.Blue
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(selectedDate : LocalDate ,
             onDateSelected: (LocalDate) -> Unit) {

    val today = LocalDate.now()
    val sunday = selectedDate.minusDays((selectedDate.dayOfWeek.value % 7).toLong())

    val days = (0..6).map { sunday.plusDays(it.toLong()) }

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 30.dp , end = 30.dp , top = 10.dp, bottom = 10.dp)
        .height(88.dp)
        .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            days.forEach() { date ->

                val isSelected = date == selectedDate
                val isFutureDate = date.isAfter(today)
                val dayName = date.dayOfWeek.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.ENGLISH).take(2)
                val isWeekend = date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(RoundedCornerShape(10))
                        .background(if (isSelected) Blue else Color.Transparent)
                        .then(
                            if (!isFutureDate) Modifier.clickable { onDateSelected(date)  }
                            else Modifier // Not clickable if future
                        )
                        .padding(vertical = 6.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = dayName,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = when {
                            isSelected -> Color.White
                            isFutureDate -> Color.Gray
                            else -> Color.Black
                        }
                    )
                    Text(
                        text = date.dayOfMonth.toString(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = when {
                            isSelected -> Color.White
                            isFutureDate -> Color.Gray
                            else -> Color.Black
                        }
                    )
                }
            }
        }
    }
}
