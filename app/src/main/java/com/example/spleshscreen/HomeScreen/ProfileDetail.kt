package com.example.spleshscreen.HomeScreen

import android.Manifest
import android.content.ContentValues
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.spleshscreen.UserDetailApi.AuthViewModel
import com.example.spleshscreen.Navigation.Screens
import com.example.spleshscreen.R
import com.example.spleshscreen.UserDetailApi.UserPreferences
import com.example.spleshscreen.ui.theme.Blue
import com.example.spleshscreen.ui.theme.LiteGray
import com.example.spleshscreen.ui.theme.Purple80
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun ProfileDetail(navController: NavController  , viewModel: AuthViewModel) {

    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }
    val user = viewModel.user

    Column( modifier = Modifier
        .fillMaxSize()
        .background(color = Blue))
    {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Profile",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = {navController.navigateUp()}) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White ,
                        modifier =Modifier.size(30.dp)
                    )
                }
            },
            actions = {
                IconButton(onClick = {
                    viewModel.logout(userPrefs)
                    navController.navigate(Screens.AuthScreen.LoginScreen.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }) {
                    Icon(painter = painterResource(R.drawable.logout) ,
                        contentDescription = "Log out" ,
                        tint = Color.White ,
                        modifier = Modifier.size(30.dp))
                }

            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Blue)
        )

        val context = LocalContext.current
        val imageUri = remember { mutableStateOf<Uri?>(null) }
        val cameraImageUri = remember { mutableStateOf<Uri?>(null) }
        val cameraPermission = rememberPermissionState(Manifest.permission.CAMERA)
        val storagePermission = rememberPermissionState(Manifest.permission.READ_MEDIA_IMAGES)
        val showDialog = remember { mutableStateOf(false) }



        val galleryLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let {
                imageUri.value = it
            }
        }

        val cameraLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture()
        ) { success : Boolean ->
            if (success){
                imageUri.value = cameraImageUri.value
            }

        }

        fun createImageUri(): Uri? {
            val resolver = context.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, "IMG_${System.currentTimeMillis()}.jpg")
                put(MediaStore.Images.Media.MIME_TYPE , "image/jpeg")
            }
            return resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        }


        val photoPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia() ,
            onResult = {
                if(it != null) {
                    imageUri.value = it
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    LiteGray,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .padding(top = 16.dp)
        ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            )
            {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    BadgedBox(
                        badge = {
                            Badge(containerColor = Color.Green, modifier = Modifier.size(10.dp))
                        }
                    ) {

                        if (imageUri.value != null) {
                                AsyncImage(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .size(100.dp)
                                        .border(width = 2.dp, color = Purple80, shape = CircleShape),
                                    model = imageUri.value,
                                    contentDescription = null
                                )
                            } else {
                                Image(
                                    painter = painterResource(R.drawable.account),
                                    contentDescription = "Default",
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .size(100.dp)
                                        .border(width = 2.dp, color = Purple80, shape = CircleShape)
                                )
                            }

                    }


                    Button(onClick = { showDialog.value = true},
                        modifier = Modifier.padding(6.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Blue)) {
                        Text(text = "Upload photo")
                    }
                    Spacer(modifier = Modifier.height(4.dp))



                        Text(
                            text = user?.let { "${it.firstname} ${it.lastname}" } ?: "Loading",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Medium
                        )


                    Text(
                        text = "Version : 1.8 ",
                        color = Color.Gray,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(
                    imageVector = Icons.Default.MailOutline,
                    contentDescription = "Email",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(30.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(text = "harry@gmail.com", color = Color.DarkGray, fontSize = 18.sp)

            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(R.drawable.person) ,
                    contentDescription = "Person" ,
                    tint = Color.DarkGray ,
                    modifier = Modifier.size(30.dp))

                Spacer(modifier = Modifier.width(12.dp))


                Text(text = "Jr.Android developer ", color = Color.DarkGray, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(10.dp))


            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Date",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))


                Text(text = "28 jan 1998", color = Color.DarkGray, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(R.drawable.call) ,
                    contentDescription = "Phone no" ,
                    tint = Color.DarkGray ,
                    modifier = Modifier.size(30.dp))

                Spacer(modifier = Modifier.width(10.dp))

                Text(text = "7878877878", color = Color.DarkGray, fontSize = 18.sp)
            }
        }
    }
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text("Select Image From") },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog.value = false
                        if (!cameraPermission.status.isGranted) {
                            cameraPermission.launchPermissionRequest()
                        } else {
                            val uri = createImageUri()
                            cameraImageUri.value = uri
                            if (uri != null) {
                                cameraLauncher.launch(uri)
                            }
                        }
                    }) {
                        Text("Camera")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDialog.value = false
                        if (!storagePermission.status.isGranted) {
                            storagePermission.launchPermissionRequest()
                        } else {
                            galleryLauncher.launch("image/*")
                        }
                    }) {
                        Text("Gallery")
                    }
                }
            )
        }

    }


}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun DetainPreview()
{
    val navController = rememberNavController()
    val viewModel: AuthViewModel = viewModel()

    ProfileDetail(navController ,viewModel)
}


