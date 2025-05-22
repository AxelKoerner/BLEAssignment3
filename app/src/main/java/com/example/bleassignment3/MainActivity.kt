package com.example.bleassignment3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bleassignment3.beacons.EddystoneBeacons
import com.example.bleassignment3.gps.GpsPositioning
import com.example.bleassignment3.ui.theme.BLEAssignment3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BLEAssignment3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = {
            val intent = Intent(context, EddystoneBeacons::class.java)
            context.startActivity(intent)
            Toast.makeText(context, "Open Eddystone Beacon Task", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Open Beacon Task")
        }
        Button(onClick = {
            val intent = Intent(context, GpsPositioning::class.java)
            context.startActivity(intent)
            Toast.makeText(context, "Open GPS Positioning Task", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Open GPS Task")
        }
    }
}
