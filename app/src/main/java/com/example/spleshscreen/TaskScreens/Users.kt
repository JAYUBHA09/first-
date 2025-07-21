package com.example.spleshscreen.TaskScreens

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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.spleshscreen.ui.theme.NavyBlue
import com.example.spleshscreen.ui.theme.Purple80
import com.example.spleshscreen.ui.theme.PurpleGrey80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Users(
    ShowDialog: MutableState<Boolean>
) {

    val ScrollState = rememberScrollState()

    val users = listOf(
        UserDetail(com.example.spleshscreen.R.drawable.account, "alex", "alex@com"),
        UserDetail(com.example.spleshscreen.R.drawable.account, "alex", "alex@com"),
        UserDetail(com.example.spleshscreen.R.drawable.account, "alex", "alex@com"),
        UserDetail(com.example.spleshscreen.R.drawable.account, "alex", "alex@com"),
        UserDetail(com.example.spleshscreen.R.drawable.account, "alex", "alex@com")
    )

    if (ShowDialog.value) {
        Dialog(onDismissRequest = { ShowDialog.value = false }) {

            Card(
                modifier = Modifier
                    .height(500.dp)
                    .background(color = PurpleGrey80, shape = RoundedCornerShape(12.dp))
            ) {
                Column(modifier = Modifier.fillMaxHeight()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(60.dp)

                            .background(color = Purple80),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Users",
                            color = NavyBlue,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding( 8.dp)
                        )

                        Icon(
                            imageVector = Icons.Default.Clear, contentDescription = "Close",
                            tint = NavyBlue,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable(onClick = { ShowDialog.value = false })
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(start = 6.dp , end = 6.dp)
                            .verticalScroll(state = ScrollState),
                        horizontalAlignment = Alignment.CenterHorizontally) {

                        users.forEach() { user ->
                            Row(
                                modifier = Modifier.fillMaxWidth()
                                    .padding(top = 4.dp , end = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Image(
                                    painter = painterResource(user.img), contentDescription = "img",
                                    modifier = Modifier.size(50.dp)
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Column(
                                    verticalArrangement = Arrangement.Center
                                )
                                {
                                    Text(
                                        text = user.name, fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = NavyBlue
                                    )


                                    Text(
                                        text = user.email,
                                        fontSize = 10.sp,
                                        color = Color.DarkGray
                                    )

                                }

                            }
                            Spacer(modifier = Modifier.height(6.dp))

                            HorizontalDivider(thickness = 2.dp)

                            Spacer(modifier = Modifier.height(6.dp))
                        }

                    }

                }
            }

        }
    }
}

data class UserDetail(val img: Int, val name: String, val email: String)

@Composable
fun UserDialog(){
    val ShowDialog = remember { mutableStateOf(true) }

    Users (ShowDialog = ShowDialog )
}