package com.example.spleshscreen.HomeScreen

import android.graphics.Rect
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun BarcodeScanner(navController: NavController) {
    val context = LocalContext.current
    val permissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    LaunchedEffect(permissionState.status) {
        if (!permissionState.status.isGranted) {
            permissionState.launchPermissionRequest()
        }
    }

    when {
        permissionState.status.isGranted -> {
            ScanQR(navController) { qrCode ->
                Log.d("QR_CODE", "Detected: $qrCode")
            }
        }

        permissionState.status.shouldShowRationale -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Camera permission is needed to scan QR codes.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { permissionState.launchPermissionRequest() }) {
                        Text("Grant Permission")
                    }
                }
            }
        }

        else -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Camera permission required.")
            }
        }
    }
}


@androidx.annotation.OptIn(ExperimentalGetImage::class)
@Composable
fun ScanQR(
    navController: NavController,
    onQrCodeDetected: (String) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var barcode by rememberSaveable { mutableStateOf<String?>(null) }
    var qrCodeDetected by remember { mutableStateOf(false) }

    val cameraController = remember {
        LifecycleCameraController(context).apply {
            setCameraSelector(CameraSelector.DEFAULT_BACK_CAMERA)
        }
    }

    // Main UI
    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                PreviewView(ctx).apply {
                    controller = cameraController

                    val options = BarcodeScannerOptions.Builder()
                        .setBarcodeFormats(
                            Barcode.FORMAT_QR_CODE,
                            Barcode.FORMAT_CODABAR,
                            Barcode.FORMAT_CODE_93,
                            Barcode.FORMAT_CODE_39,
                            Barcode.FORMAT_CODE_128,
                            Barcode.FORMAT_EAN_8,
                            Barcode.FORMAT_EAN_13,
                            Barcode.FORMAT_AZTEC
                        ).build()

                    val barcodeScanner = BarcodeScanning.getClient(options)

                    cameraController.setImageAnalysisAnalyzer(
                        ContextCompat.getMainExecutor(ctx),
                        MlKitAnalyzer(
                            listOf(barcodeScanner),
                            ImageAnalysis.COORDINATE_SYSTEM_VIEW_REFERENCED,
                            ContextCompat.getMainExecutor(ctx),
                        ) { result ->
                            val barcodeResults = result?.getValue(barcodeScanner)
                            if (!barcodeResults.isNullOrEmpty() && !qrCodeDetected) {
                                val detectedCode = barcodeResults.first().rawValue
                                if (!detectedCode.isNullOrEmpty() && detectedCode != barcode) {
                                    barcode = detectedCode
                                    qrCodeDetected = true
                                    Log.d("Detected QR", barcode!!)
                                    onQrCodeDetected(barcode!!)
                                    navController.popBackStack()
                                }
                            }
                        }
                    )

                    // Bind lifecycle here
                    cameraController.bindToLifecycle(lifecycleOwner)
                }
            }
        )

        // Scanning Frame
        Canvas(modifier = Modifier.fillMaxSize()) {
            val frameWidth = size.width * 0.6f
            val frameHeight = frameWidth
            val topLeftX = (size.width - frameWidth) / 2f
            val topLeftY = (size.height - frameHeight) / 2f

            drawRoundRect(
                color = Color.Green,
                topLeft = Offset(topLeftX, topLeftY),
                size = Size(frameWidth, frameHeight),
                cornerRadius = CornerRadius(16f, 16f),
                style = Stroke(width = 6f)
            )
        }

        // Scanned text
        barcode?.let {
            Text(
                text = "Scanned: $it",
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(Color.Black.copy(alpha = 0.6f))
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
    }

    // Clean-up if needed
    DisposableEffect(lifecycleOwner) {
        onDispose {
            cameraController.unbind()
        }
    }
}


//
//private fun barcodeProcess(
//    context: Context,
//    success: (String) -> Unit,
//    canceled: () -> Unit,
//    failed: (Exception) -> Unit
//) {
//    val options = GmsBarcodeScannerOptions.Builder()
//        .setBarcodeFormats(
//            Barcode.FORMAT_QR_CODE,
//            Barcode.FORMAT_AZTEC
//        )
//        .enableAutoZoom()
//        .build()
//
//    val scanner = GmsBarcodeScanning.getClient(context, options)
//
//    scanner.startScan()
//        .addOnSuccessListener { barcode ->
//            barcode.displayValue?.let { success(it) }
//            Log.d("BarcodeScan", "Success: ${barcode.displayValue}")
//        }
//        .addOnCanceledListener {
//            canceled()
//            Log.d("BarcodeScan", "Canceled")
//        }
//        .addOnFailureListener { e ->
//            failed(e)
//            Log.e("BarcodeScan", "Failed", e)
//        }
//}
//
//@OptIn(ExperimentalPermissionsApi::class)
//@Composable
//fun BarcodeScannerr(navController: NavController) {
//    val context = LocalContext.current
//    val cameraPermission = rememberPermissionState(android.Manifest.permission.CAMERA)
//    val hasScanned = remember { mutableStateOf(false) }
//    val scannedValue = remember { mutableStateOf<String?>(null) }
//    val showDialog = remember { mutableStateOf(false) }
//
//
//    // Start scanning when permission granted
//    LaunchedEffect(cameraPermission.status, hasScanned.value) {
//        if (cameraPermission.status.isGranted && !hasScanned.value) {
//            hasScanned.value = true
//            barcodeProcess(
//                context = context,
//                success = {
//                    scannedValue.value = it
//                    showDialog.value = true
//                },
//                canceled = {
//                    scannedValue.value = "Scan canceled"
//                    showDialog.value = true
//                },
//                failed = { ex ->
//                    scannedValue.value = "Scan failed: ${ex.localizedMessage}"
//                    showDialog.value = true
//                }
//            )
//        }
//    }
//
//    // UI
//    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        when {
//            !cameraPermission.status.isGranted -> {
//                Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                    Text("Camera permission is required to scan barcodes.")
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Button(onClick = { cameraPermission.launchPermissionRequest() }) {
//                        Text("Grant Permission")
//                    }
//                }
//            }
//
//            scannedValue.value != null && showDialog.value -> {
//                AlertDialog(
//                    onDismissRequest = { showDialog.value = false },
//                    title = { Text("Scan Result") },
//                    text = { Text(scannedValue.value ?: "No result") },
//                    confirmButton = {
//                        Button(onClick = {
//                            showDialog.value = false
//                            navController.popBackStack()
//                        }) {
//                            Text("OK")
//                        }
//                    },
//                    dismissButton = {
//                        Button(onClick = {
//                            showDialog.value = false
//                            hasScanned.value = false
//                        }) {
//                            Text("Scan Again")
//                        }
//                    }
//                )
//            }
//
//            else -> {
//                CircularProgressIndicator()
//            }
//        }
//    }
//}