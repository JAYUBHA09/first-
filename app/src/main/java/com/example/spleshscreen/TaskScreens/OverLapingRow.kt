package com.example.spleshscreen.TaskScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.spleshscreen.R



@Composable
fun OverLapingRow() {

    val ShowDialog = remember { mutableStateOf(false) }

    Users (ShowDialog = ShowDialog )



    val images = listOf<Image>(
        Image(R.drawable.account),
        Image(R.drawable.account),
        Image(R.drawable.account),
        Image(R.drawable.account),
        Image(R.drawable.account)

    )

    Box(modifier = Modifier.fillMaxWidth() ) {

        images.forEachIndexed { index, imageRes ->

            Image(
                painter = painterResource(id = imageRes.img),
                contentDescription = null,
                modifier = Modifier
                    .size(55.dp)
                    .offset(x = (index * 28).dp) // overlaps by 15dp
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
    Box(modifier = Modifier.size(55.dp)
        .offset(x= (images.size* 28).dp)
        .clip(CircleShape)
        .background(Color(0xFF3F51B5))
        .border(width = 2.dp , color = Color.White , shape = CircleShape)
        .zIndex(images.size.toFloat()),
        contentAlignment = Alignment.Center)
    {
        Text(
        text = "6+",
        color = Color.White,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.clickable(onClick = {ShowDialog.value = true})
    )

    }


}


data class Image(val img : Int)