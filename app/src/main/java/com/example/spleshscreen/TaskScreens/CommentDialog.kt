package com.example.spleshscreen.TaskScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.spleshscreen.ui.theme.Orange
import com.example.spleshscreen.ui.theme.Purple80
import com.example.spleshscreen.ui.theme.PurpleGrey80


@Composable
fun CommentDialog(
    ShowDialog: MutableState<Boolean>
) {

    if (ShowDialog.value) {

        Dialog(onDismissRequest = {ShowDialog.value = false }) {
            Card(
                modifier = Modifier
                    .background(color = PurpleGrey80, shape = RoundedCornerShape(12.dp))
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(50.dp)
                            .background(color = Purple80),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Write Comments",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(8.dp)
                        )

                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "close",
                            modifier = Modifier.padding(8.dp)
                                .clickable(onClick = {ShowDialog.value = false})
                        )

                    }

                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.cardElevation(10.dp)

                    ) {


                        OutlinedTextField(
                            label = { Text("What is your review of this?") },
                            value = String(),
                            onValueChange = {},
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent
                            ),
                            modifier = Modifier.fillMaxWidth()
                                .height(150.dp)


                        )


                    }

                    Button(
                        onClick = { ShowDialog.value = false},
                        modifier = Modifier.padding(12.dp)
                            .align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Orange
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Submit")
                    }


                }

            }

        }

    }
}


@Composable
fun CommentDialogPreview() {

    val ShowDialog = remember { mutableStateOf(false) }

    CommentDialog(ShowDialog = ShowDialog)

}
