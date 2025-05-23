package com.example.bleassignment3.beacons

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.example.bleassignment3.ui.theme.BLEAssignment3Theme


class EddystoneBeacons : ComponentActivity() {
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private var isCurrentlyScanning = false


    @RequiresPermission(Manifest.permission.BLUETOOTH_SCAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BLEAssignment3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BeaconContent(Modifier.padding(innerPadding))
                }
            }
        }
    }
    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.all { it.value }) {
            // All permissions granted
        } else {
            println("Permission denied")
        }
    }
    val requiredPermissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        arrayOf(
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
    } else {
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BLUETOOTH_ADMIN
        )
    }

    fun hasPermissions(): Boolean {
        return requiredPermissions.all {
            ContextCompat.checkSelfPermission(
                baseContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private var leScanCallback: BluetoothAdapter.LeScanCallback = object :
        BluetoothAdapter.LeScanCallback {
        override fun onLeScan(
            device: BluetoothDevice?,
            rssi: Int,
            scanRecord: ByteArray?
        ) {
            TODO("Not yet implemented")
        }


    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_SCAN)
    fun toggleScanning() {
        if(hasPermissions()) {
            if (isCurrentlyScanning) {
                bluetoothAdapter?.stopLeScan(leScanCallback)
            } else {
                bluetoothAdapter?.startLeScan(leScanCallback)
            }
            isCurrentlyScanning = !isCurrentlyScanning
        } else {
            requestPermissionLauncher.launch(requiredPermissions)
        }
    }


    @RequiresPermission(Manifest.permission.BLUETOOTH_SCAN)
    @Composable
    fun BeaconContent(modifier: Modifier) {
        var isScanning by remember { mutableStateOf(false)}
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick =  {
                isScanning = !isScanning
                toggleScanning()
            }) {
                Text(if (isScanning) "Stop Scanning" else "Start Scanning")
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {

        }
    }
}

